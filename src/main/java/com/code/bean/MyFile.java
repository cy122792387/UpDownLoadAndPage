package com.code.bean;

public class MyFile {
    String fileName;
    String filePath;
    
    public MyFile() {
    }
    
    public MyFile(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public String getFilePath() {
        return filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    @Override
    public String toString() {
        return "MyFile{" +
            "fileName='" + fileName + '\'' +
            ", filePath='" + filePath + '\'' +
            '}';
    }
}
