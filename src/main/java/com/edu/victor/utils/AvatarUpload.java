package com.edu.victor.utils;

import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 处理头像的文件上传*/
public class AvatarUpload {
    private static String baseUrl = "E:\\avatar\\";
    private static List<String> suffixes = new ArrayList<>();
    static {
        suffixes.add("jpg");
        suffixes.add("png");
    }
    public static String save(MultipartFile multipartFile,String id) throws UnsupportedFileTypeException {
        /**判断文件类型是否符合规定*/
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if(!suffixes.contains(suffix))
            throw new UnsupportedFileTypeException();
            //获取子路径
        int dir1 = id.hashCode()&0xf;
        int dir2 = id.hashCode()&0xf0;

        String path = dir1+File.separator+dir2+File.separator+id+"_teacher."+suffix;
        //创建目录
        String catalog = baseUrl + path;
        File catalogFile = new File(catalog);
        if(!catalogFile.getParentFile().exists()){
            catalogFile.mkdirs();
        }
        try {
            multipartFile.transferTo(catalogFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
    public static void main(String[] args){
        File file = new File("E:\\avatar\\5\\128\\1569048950502.jpg");
        file.delete();
    }

}
