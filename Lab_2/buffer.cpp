/**
 * @author See Contributors.txt for code contributors and overview of BadgerDB.
 *
 * @section LICENSE
 * Copyright (c) 2012 Database Group, Computer Sciences Department, University of Wisconsin-Madison.
 */

#include <memory>
#include <iostream>
#include "buffer.h"
#include "exceptions/buffer_exceeded_exception.h"
#include "exceptions/page_not_pinned_exception.h"
#include "exceptions/page_pinned_exception.h"
#include "exceptions/bad_buffer_exception.h"
#include "exceptions/hash_not_found_exception.h"

namespace badgerdb {

BufMgr::BufMgr(std::uint32_t bufs)
	: numBufs(bufs) {
	bufDescTable = new BufDesc[bufs];

  for (FrameId i = 0; i < bufs; i++)
  {
  	bufDescTable[i].frameNo = i;
  	bufDescTable[i].valid = false;
  }

  bufPool = new Page[bufs];

	int htsize = ((((int) (bufs * 1.2))*2)/2)+1;
  hashTable = new BufHashTbl (htsize);  // allocate the buffer hash table

  clockHand = bufs - 1;
}

// 清除所有脏页并释放缓冲池和BufDesc表
BufMgr::~BufMgr() {
    for(unsigned int i = 0; i < numBufs; i++){
        if(bufDescTable[i].dirty == true){
            flushFile(bufDescTable[i].file);
        }
    }
    delete [] bufDescTable;
    delete [] bufPool;
    delete hashTable;
}

// 使指针指向缓冲池的下一页
void BufMgr::advanceClock()
{
    clockHand = (clockHand+1) % numBufs;
}

// 分配缓冲池中的页面，判断valid，refbit，pincnt
void BufMgr::allocBuf(FrameId & frame)
{
    bool findNext = false;
    unsigned int pinned = 0;
    while(!findNext){
        advanceClock();
        if(pinned == numBufs){
            throw BufferExceededException();
        }
        if (bufDescTable[clockHand].valid == false){
            bufDescTable[clockHand].Clear();
            frame = clockHand;
            findNext = true;
        }
        else if(bufDescTable[clockHand].refbit == true){
            bufDescTable[clockHand].refbit = false;
        }
        else if(bufDescTable[clockHand].pinCnt > 0){
            pinned++;
        }

        else if(bufDescTable[clockHand].refbit == false){
            if(bufDescTable[clockHand].dirty == true){
                bufDescTable[clockHand].file->writePage(bufPool[clockHand]);
            }
            hashTable->remove(bufDescTable[clockHand].file, bufDescTable[clockHand].pageNo);
            bufDescTable[clockHand].Clear();
            frame = clockHand;
            findNext = true;
        }
    }
}

// 读取页面
// 页面在缓冲池中则设置refbit，pin；若不在则分配从缓冲池中分配页面，设置pincnt，从硬盘读取页面
void BufMgr::readPage(File* file, const PageId pageNo, Page*& page)
{
    FrameId frame;
    try{
        hashTable->lookup(file, pageNo, frame);
        bufDescTable[frame].refbit = true;
        bufDescTable[frame].pinCnt++;
        page = &bufPool[frame];

    }catch(HashNotFoundException e){
        FrameId frame;
        allocBuf(frame);
        bufPool[frame] = file->readPage(pageNo);
        hashTable->insert(file, pageNo, frame);
        bufDescTable[frame].Set(file, pageNo);
        page = &bufPool[frame];
    }
}

// 将pin位释放
// 若有脏页则进行相应设置，pincnt--，pin计数为零则抛出异常
void BufMgr::unPinPage(File* file, const PageId pageNo, const bool dirty)
{
  FrameId frame;
    try{
        hashTable->lookup(file, pageNo, frame);
        if(bufDescTable[frame].pinCnt > 0){
            bufDescTable[frame].pinCnt--;
            if (dirty == true){
                bufDescTable[frame].dirty = dirty;
            }
        }
        else{
            throw PageNotPinnedException("Ping已经为0", pageNo, frame);
        }
    }catch(HashNotFoundException e){

    }
}

// 将文件写回磁盘
// 若页面为脏，则写回；从哈希表中删除相应的页面；对相应页面调用clear
void BufMgr::flushFile(const File* file)
{
    for(unsigned int i = 0; i < numBufs; i++){
        if(bufDescTable[i].file == file){
            if(bufDescTable[i].pinCnt != 0){
                throw PagePinnedException("This removing page is already being used", bufDescTable[i].pageNo, bufDescTable[i].frameNo);
            }
            if(bufDescTable[i].valid == false){
                throw BadBufferException(bufDescTable[i].frameNo, bufDescTable[i].dirty, false, bufDescTable[i].refbit);
            }
            if(bufDescTable[i].dirty == true){
                bufDescTable[i].file->writePage(bufPool[i]);
                bufDescTable[i].dirty = false;
            }
            hashTable->remove(file, bufPool[i].page_number());
            bufDescTable[i].Clear();
        }
    }
}

// 分配新页
// 通过pageNo返回新分配的序号，通过page返回对应于缓冲区frame的指针
void BufMgr::allocPage(File* file, PageId &pageNo, Page*& page)
{
    FrameId frame;
    Page new_page = file->allocatePage();
    allocBuf(frame);
    hashTable->insert(file,new_page.page_number(), frame);
    bufDescTable[frame].Set(file, new_page.page_number());
    pageNo = new_page.page_number();
    bufPool[frame] = new_page;
    page = &bufPool[frame];
}

// 从文件中删除特定页面，若该页面在缓冲池中分配了frame则将该frame释放并且从哈希表中删除
void BufMgr::disposePage(File* file, const PageId PageNo)
{
    FrameId frame;
    try{
        hashTable->lookup(file, PageNo, frame);
        bufDescTable[frame].Clear();
        file->deletePage(PageNo);
        hashTable->remove(file, PageNo);
    }catch(HashNotFoundException e){
        file->deletePage(PageNo);
    }
}

void BufMgr::printSelf(void)
{
  BufDesc* tmpbuf;
	int validFrames = 0;

  for (std::uint32_t i = 0; i < numBufs; i++)
	{
  	tmpbuf = &(bufDescTable[i]);
		std::cout << "FrameNo:" << i << " ";
		tmpbuf->Print();

  	if (tmpbuf->valid == true)
    	validFrames++;
  }

	std::cout << "Total Number of Valid Frames:" << validFrames << "\n";
}

}
