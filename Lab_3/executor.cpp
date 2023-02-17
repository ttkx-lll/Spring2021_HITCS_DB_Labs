/**
 * @author Zhaonian Zou <znzou@hit.edu.cn>,
 * School of Computer Science and Technology,
 * Harbin Institute of Technology, China
 */

#include "executor.h"

#include "exceptions/buffer_exceeded_exception.h"
#include <cmath>
#include <ctime>
#include <vector>
#include <functional>
#include <iostream>
#include <string>
#include <utility>
#include <map>
#include <algorithm>

#include "file_iterator.h"
#include "page_iterator.h"
#include "storage.h"

using namespace std;

namespace badgerdb {

void TableScanner::print() const {
  badgerdb::File file = badgerdb::File::open(tableFile.filename());
  for (badgerdb::FileIterator iter = file.begin(); iter != file.end(); ++iter) {
    badgerdb::Page page = *iter;
    badgerdb::Page* buffered_page;
    bufMgr->readPage(&file, page.page_number(), buffered_page);

    for (badgerdb::PageIterator page_iter = buffered_page->begin();
         page_iter != buffered_page->end(); ++page_iter) {
      string key = *page_iter;
      string print_key = "(";
      int current_index = 0;
      for (int i = 0; i < tableSchema.getAttrCount(); ++i) {
        switch (tableSchema.getAttrType(i)) {
          case INT: {
            int true_value = 0;
            for (int j = 0; j < 4; ++j) {
              if (std::string(key, current_index + j, 1)[0] == '\0') {
                continue;  // \0 is actually representing 0
              }
              true_value +=
                  (std::string(key, current_index + j, 1))[0] * pow(128, 3 - j);
            }
            print_key += to_string(true_value);
            current_index += 4;
            break;
          }
          case CHAR: {
            int max_len = tableSchema.getAttrMaxSize(i);
            print_key += std::string(key, current_index, max_len);
            current_index += max_len;
            current_index +=
                (4 - (max_len % 4)) % 4;  // align to the multiple of 4
            break;
          }
          case VARCHAR: {
            int actual_len = key[current_index];
            current_index++;
            print_key += std::string(key, current_index, actual_len);
            current_index += actual_len;
            current_index +=
                (4 - ((actual_len + 1) % 4)) % 4;  // align to the multiple of 4
            break;
          }
        }
        print_key += ",";
      }
      print_key[print_key.size() - 1] = ')';  // change the last ',' to ')'
      cout << print_key << endl;
    }
    bufMgr->unPinPage(&file, page.page_number(), false);
  }
  bufMgr->flushFile(&file);
}

JoinOperator::JoinOperator(File& leftTableFile,
                           File& rightTableFile,
                           const TableSchema& leftTableSchema,
                           const TableSchema& rightTableSchema,
                           const Catalog* catalog,
                           BufMgr* bufMgr)
    : leftTableFile(leftTableFile),
      rightTableFile(rightTableFile),
      leftTableSchema(leftTableSchema),
      rightTableSchema(rightTableSchema),
      resultTableSchema(
          createResultTableSchema(leftTableSchema, rightTableSchema)),
      catalog(catalog),
      bufMgr(bufMgr),
      isComplete(false) {
  // nothing
}

TableSchema JoinOperator::createResultTableSchema(
    const TableSchema& leftTableSchema,
    const TableSchema& rightTableSchema) {
  vector<Attribute> attrs;

  // first add all the left table attrs to the result table
  for (int k = 0; k < leftTableSchema.getAttrCount(); ++k) {
    Attribute new_attr = Attribute(
        leftTableSchema.getAttrName(k), leftTableSchema.getAttrType(k),
        leftTableSchema.getAttrMaxSize(k), leftTableSchema.isAttrNotNull(k),
        leftTableSchema.isAttrUnique(k));
    attrs.push_back(new_attr);
  }

  // test every right table attrs, if it doesn't have the same attr(name and
  // type) in the left table, then add it to the result table
  for (int i = 0; i < rightTableSchema.getAttrCount(); ++i) {
    bool has_same = false;
    for (int j = 0; j < leftTableSchema.getAttrCount(); ++j) {
      if ((leftTableSchema.getAttrType(j) == rightTableSchema.getAttrType(i)) &&
          (leftTableSchema.getAttrName(j) == rightTableSchema.getAttrName(i))) {
        has_same = true;
      }
    }
    if (!has_same) {
      Attribute new_attr = Attribute(
          rightTableSchema.getAttrName(i), rightTableSchema.getAttrType(i),
          rightTableSchema.getAttrMaxSize(i), rightTableSchema.isAttrNotNull(i),
          rightTableSchema.isAttrUnique(i));
      attrs.push_back(new_attr);
    }
  }
  return TableSchema("TEMP_TABLE", attrs, true);
}

void JoinOperator::printRunningStats() const {
  cout << "# Result Tuples: " << numResultTuples << endl;
  cout << "# Used Buffer Pages: " << numUsedBufPages << endl;
  cout << "# I/Os: " << numIOs << endl;
}

vector<Attribute> JoinOperator::getCommonAttributes(
    const TableSchema& leftTableSchema,
    const TableSchema& rightTableSchema) const {
  vector<Attribute> common_attrs;
  for (int i = 0; i < rightTableSchema.getAttrCount(); ++i) {
    for (int j = 0; j < leftTableSchema.getAttrCount(); ++j) {
      if ((leftTableSchema.getAttrType(j) == rightTableSchema.getAttrType(i)) &&
          (leftTableSchema.getAttrName(j) == rightTableSchema.getAttrName(i))) {
        Attribute new_attr = Attribute(rightTableSchema.getAttrName(i),
                                       rightTableSchema.getAttrType(i),
                                       rightTableSchema.getAttrMaxSize(i),
                                       rightTableSchema.isAttrNotNull(i),
                                       rightTableSchema.isAttrUnique(i));
        common_attrs.push_back(new_attr);
      }
    }
  }
  return common_attrs;
}

/**
 * use the original key to generate the search key
 * @param key
 * @param common_attrs
 * @param TableSchema
 * @return
 */
string construct_search_key(string key,
                            vector<Attribute>& common_attrs,
                            const TableSchema& TableSchema) {
  string search_key;
  int current_index = 0;
  int current_attr_index = 0;
  for (int i = 0; i < TableSchema.getAttrCount(); ++i) {
    switch (TableSchema.getAttrType(i)) {
      case INT: {
        if (TableSchema.getAttrName(i) ==
                common_attrs[current_attr_index].attrName &&
            TableSchema.getAttrType(i) ==
                common_attrs[current_attr_index].attrType) {
          search_key += std::string(key, current_index, 4);
          current_attr_index++;
        }
        current_index += 4;
        break;
      }
      case CHAR: {
        int max_len = TableSchema.getAttrMaxSize(i);
        if (TableSchema.getAttrName(i) ==
                common_attrs[current_attr_index].attrName &&
            TableSchema.getAttrType(i) ==
                common_attrs[current_attr_index].attrType) {
          search_key += std::string(key, current_index, max_len);
          current_attr_index++;
        }
        current_index += max_len;
        current_index += (4 - (max_len % 4)) % 4;
        ;  // align to the multiple of 4
        break;
      }
      case VARCHAR: {
        int actual_len = key[current_index];
        current_index++;
        if (TableSchema.getAttrName(i) ==
                common_attrs[current_attr_index].attrName &&
            TableSchema.getAttrType(i) ==
                common_attrs[current_attr_index].attrType) {
          search_key += std::string(key, current_index, actual_len);
          current_attr_index++;
        }
        current_index += actual_len;
        current_index +=
            (4 - ((actual_len + 1) % 4)) % 4;  // align to the multiple of 4
        break;
      }
    }
    if (current_attr_index >= common_attrs.size())
      break;
  }
  return search_key;
}

string JoinOperator::joinTuples(string leftTuple,
                                string rightTuple,
                                const TableSchema& leftTableSchema,
                                const TableSchema& rightTableSchema) const {
  int cur_right_index = 0;  // current substring index in the right table key
  string result_tuple = leftTuple;

  for (int i = 0; i < rightTableSchema.getAttrCount(); ++i) {
    bool has_same = false;
    for (int j = 0; j < leftTableSchema.getAttrCount(); ++j) {
      if ((leftTableSchema.getAttrType(j) == rightTableSchema.getAttrType(i)) &&
          (leftTableSchema.getAttrName(j) == rightTableSchema.getAttrName(i))) {
        has_same = true;
      }
    }
    // if the key is only owned by right table, add it to the result tuple
    switch (rightTableSchema.getAttrType(i)) {
      case INT: {
        if (!has_same) {
          result_tuple += std::string(rightTuple, cur_right_index, 4);
        }
        cur_right_index += 4;
        break;
      }
      case CHAR: {
        int max_len = rightTableSchema.getAttrMaxSize(i);
        if (!has_same) {
          result_tuple += std::string(rightTuple, cur_right_index, max_len);
        }
        cur_right_index += max_len;
        unsigned align_ = (4 - (max_len % 4)) % 4;  // align to the multiple of
                                                    // 4
        for (int k = 0; k < align_; ++k) {
          result_tuple += "0";
          cur_right_index++;
        }
        break;
      }
      case VARCHAR: {
        int actual_len = rightTuple[cur_right_index];
        result_tuple += std::string(rightTuple, cur_right_index, 1);
        cur_right_index++;
        if (!has_same) {
          result_tuple += std::string(rightTuple, cur_right_index, actual_len);
        }
        cur_right_index += actual_len;
        unsigned align_ =
            (4 - ((actual_len + 1) % 4)) % 4;  // align to the multiple of 4
        for (int k = 0; k < align_; ++k) {
          result_tuple += "0";
          cur_right_index++;
        }
        break;
      }
    }
  }
  return result_tuple;
}

bool OnePassJoinOperator::execute(int numAvailableBufPages, File& resultFile) {
  if (isComplete)
    return true;

  numResultTuples = 0;
  numUsedBufPages = 0;
  numIOs = 0;

  // TODO: Execute the join algorithm

  isComplete = true;
  return true;
}

bool NestedLoopJoinOperator::execute(int numAvailableBufPages,
                                     File& resultFile) {
  if (isComplete)
    return true;

  numResultTuples = 0;
  numUsedBufPages = 0;
  numIOs = 0;
  vector<PageId> usedPage;// already used page
  vector<Page> already_in_buf;//using page
  vector<Attribute> common_attrs;
  map<string, vector<string>> hashMap;// recordid -> {hashstirng, last/*contains head*/}
  
  common_attrs = getCommonAttributes(leftTableSchema, rightTableSchema);

  badgerdb::File rightfile = badgerdb::File::open(catalog->getTableFilename(catalog->getTableId(rightTableSchema.getTableName())));
  badgerdb::File leftfile = badgerdb::File::open(catalog->getTableFilename(catalog->getTableId(leftTableSchema.getTableName())));
  int read_page_num = 0;
  int usedPageNum = 0;
  int sum = 0;

  // 统计外关系文件的块数
  for (FileIterator iter = rightfile.begin();
      iter != rightfile.end();
      ++iter) {
      sum++;
  }

  while (usedPageNum < sum) {
      for (FileIterator iter = rightfile.begin();
          iter != rightfile.end();
          ++iter) {
          // 从外关系中取M-1块放入内存中
          if (read_page_num >= numAvailableBufPages - 1)
              break;
          PageId pagenum = (*iter).page_number();

          if (count(usedPage.begin(), usedPage.end(), pagenum)) //FOUND
              continue;
          //NOT Found
          Page* new_page;
          bufMgr->readPage(&rightfile, pagenum, new_page);
          for (PageIterator page_iter = (*new_page).begin();// read all tuples
              page_iter != (*new_page).end();
              ++page_iter) {
              string righttuple = *page_iter;
              string searchKey = construct_search_key(righttuple, common_attrs, rightTableSchema);

              if (hashMap.count(searchKey) == 1) {
                  hashMap[searchKey].push_back(righttuple);
              }
              else {
                  vector<string> stringList;
                  stringList.push_back(righttuple);
                  hashMap.insert(pair<string, vector<string>>(searchKey, stringList));
              }

          }
          usedPage.push_back(pagenum);
          already_in_buf.push_back(*new_page); // new or old?
          numIOs++;
          numUsedBufPages++;
          read_page_num++;
          usedPageNum += read_page_num;
      }
      for (FileIterator iter = leftfile.begin();
          iter != leftfile.end();
          ++iter) {
          Page* left_new_page;
          Page p = *iter;
          PageId leftPagenum = p.page_number();
          bufMgr->readPage(&leftfile, leftPagenum, left_new_page);
          p = *left_new_page;
          numUsedBufPages++;
          numIOs++;
          for (PageIterator page_iter = p.begin();// read all tuples
              page_iter != p.end();
              ++page_iter) {
              string lefttuple = *page_iter;
              string searchKey = construct_search_key(lefttuple, common_attrs, leftTableSchema);
              if (hashMap.count(searchKey) == 1) {
                  vector<string> same = hashMap[searchKey];
                  for (unsigned int i = 0; i < same.size(); i++) {
                      numResultTuples++;
                      string resultString = joinTuples(lefttuple, same[i], leftTableSchema, rightTableSchema);
                      HeapFileManager::insertTuple(resultString, resultFile, bufMgr);
                  }
              }
          }
          bufMgr->unPinPage(&leftfile, leftPagenum, false);
          numUsedBufPages--;
      }
      for (unsigned int i = 0; i < already_in_buf.size(); i++) {
          bufMgr->unPinPage(&rightfile, already_in_buf[i].page_number(), false);
      }
      bufMgr->flushFile(&rightfile);
      already_in_buf.clear();
      read_page_num = 0;
  }
  numUsedBufPages++;
  isComplete = true;
  return true;
}

BucketId GraceHashJoinOperator::hash(const string& key) const {
  std::hash<string> strHash;
  return strHash(key) % numBuckets;
}

bool GraceHashJoinOperator::execute(int numAvailableBufPages,
                                    File& resultFile) {
  if (isComplete)
    return true;

  numResultTuples = 0;
  numUsedBufPages = 0;
  numIOs = 0;

  // TODO: Execute the join algorithm

  isComplete = true;
  return true;
}

}  // namespace badgerdb
