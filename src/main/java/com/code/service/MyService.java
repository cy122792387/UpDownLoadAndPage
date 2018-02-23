package com.code.service;

import com.code.bean.MyFile;
import com.code.entity.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MyService {
    public void insertFile(MyFile myFile);
    
    public List<MyFile> queryAll();
    
    public List<MyFile> queryByPage(int startPos, int pageSize);
    
    public int getFilesCount();
}
