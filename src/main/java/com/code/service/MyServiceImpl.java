package com.code.service;

import com.code.bean.MyFile;
import com.code.entity.Page;
import com.code.mapper.MyFileMapper;
import com.code.util.DBUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyServiceImpl implements MyService {
    SqlSessionFactory sqlSessionFactory = DBUtil.getSqlSessionFactory();
    SqlSession sqlSession;
    MyFileMapper myFileMapper;
    
    private void before() {
        sqlSession = sqlSessionFactory.openSession();
        myFileMapper = sqlSession.getMapper(MyFileMapper.class);
    }
    
    private void after() {
        sqlSession.commit();
        sqlSession.close();
    }
    
    public void insertFile(MyFile myFile) {
        before();
        myFileMapper.insertFile(myFile);
        after();
    }
    
    public List<MyFile> queryAll() {
        before();
        List<MyFile> list = myFileMapper.queryAll();
        after();
        return list;
    }
    
    public List<MyFile> queryByPage(int startPos, int pageSize) {
        before();
        List<MyFile> list = myFileMapper.queryByPage(startPos, pageSize);
        after();
        return list;
    }
    
    public int getFilesCount() {
        before();
        int i = myFileMapper.getFilesCount();
        after();
        return i;
    }
}
