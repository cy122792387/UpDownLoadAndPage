package com.test;

import com.code.bean.MyFile;
import com.code.mapper.MyFileMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class mybatisTest {
    private SqlSessionFactory sqlSessionFactory;
    
    @Before
    public void init() {
        try {
            //加载配置文件
            String resource = "mybatis.cfg.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            //创建会话工厂
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testInsertFile() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MyFileMapper myFileMapper = sqlSession.getMapper(MyFileMapper.class);
        myFileMapper.insertFile(new MyFile("aaa", "bbb"));
        sqlSession.commit();
        sqlSession.close();
    }
}
