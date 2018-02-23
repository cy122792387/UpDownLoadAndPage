package com.code.controller;

import com.code.bean.MyFile;
import com.code.entity.Page;
import com.code.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
public class UpDownLoadController {
    @Autowired
    MyService service;
    
    /**
     * 上传图片，保存在相应的目录下。
     * */
    @RequestMapping(value = "/upload.do", method = RequestMethod.POST)
    public String upload(@RequestParam MultipartFile[] myfiles, HttpServletRequest request) throws IOException {
        
        for (MultipartFile file : myfiles) {
            //此处MultipartFile[]表明是多文件,如果是单文件MultipartFile就行了
            if (file.isEmpty()) {
                System.out.println("文件未上传!");
            } else {
                //MyService
                //得到上传的文件名
                String fileName = file.getOriginalFilename();
                //得到服务器项目发布运行所在地址
                String path1 = request.getSession().getServletContext().getRealPath("WEB-INF/jsp") + File.separator;
                String path = path1 + fileName;
                //打印文件上传路径，方便查看是否上传成功
                System.out.println(path);
                //把文件上传至path的路径
                File localFile = new File(path);
                file.transferTo(localFile);
                
                //dao
                /*SqlSessionFactory sqlSessionFactory = DBUtil.getSqlSessionFactory();
                SqlSession sqlSession = sqlSessionFactory.openSession();
                MyFileMapper myFileMapper = sqlSession.getMapper(MyFileMapper.class);
                myFileMapper.insertFile(new MyFile(fileName, path));
                sqlSession.commit();
                sqlSession.close();*/
                service.insertFile(new MyFile(fileName, path));
            }
        }
        return "uploadSuccess";
    }
    
    @RequestMapping("/download.do")
    public String download(String fileName, String path, HttpServletRequest request,
                           HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="
            + fileName);
        try {
            InputStream inputStream = new FileInputStream(new File(path));
            
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            
            // 这里主要关闭。
            os.close();
            
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  返回值要注意，要不然就出现下面这句错误！
        //java+getOutputStream() has already been called for this response
        return null;
    }
    
    @RequestMapping("/toupload")
    public String toUpLoad() {
        return "upload";
    }
    
    /*    @RequestMapping("/todownload")
        public String toDownLoad(Model model) {
            List<MyFile> list = service.queryAll();
            model.addAttribute("list", list);
            return "list";
        }*/
    @RequestMapping("/todownload")
    public String toDownLoad(HttpServletRequest request, Model model) {
        String pageNow = request.getParameter("pageNow");
        Page page;
        List<MyFile> list;
        int totalCount = service.getFilesCount();
        if (pageNow != null) {
            page = new Page(totalCount, Integer.parseInt(pageNow));
            list = this.service.queryByPage(page.getStartPos(), page.getPageSize());
        } else {
            page = new Page(totalCount, 1);
            list = this.service.queryByPage(page.getStartPos(), page.getPageSize());
        }
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        return "list";
    }
}
