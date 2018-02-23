package com.code.mapper;

import com.code.bean.MyFile;
import com.code.entity.Page;

import java.util.List;

public interface MyFileMapper {
    public void insertFile(MyFile myFile);
    
    public List<MyFile> queryAll();
    
    public List<MyFile> queryByPage(int startPos, int pageSize);
    
    public int getFilesCount();
}
