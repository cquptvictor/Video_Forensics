package com.edu.victor.utils;

import com.edu.victor.Exception.UnsupportedFileTypeException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理video、avatar、courseImage、courseware的上传*/
public class UploadUtils {
    private static String avatarBaseUrl = "E:\\netClass\\avatar\\";
    private static String courseImageBaseUrl = "E:\\netClass\\course\\";
    private static String courseVideoBaseUrl = "E:\\netClass\\video\\";
    private static String coursewareBaseUrl = "E:\\netClass\\courseware\\";
    private static List<String> imageSuffixes = new ArrayList<>();
    private static List<String> videoSuffixes = new ArrayList<>();
    private static List<String> coursewareSuffixes = new ArrayList<>();
    /**添加类型限制*/
    static {
        imageSuffixes.add("jpg");
        imageSuffixes.add("png");
        videoSuffixes.add("mp4");
        coursewareSuffixes.add("pptx");
    }
    public static String saveImage(MultipartFile multipartFile,String id,String type) throws UnsupportedFileTypeException {
        /**判断文件类型是否符合规定*/
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if(type.equals("video")){
            if(!videoSuffixes.contains(suffix))
                throw new UnsupportedFileTypeException();
        }else if(type.equals("courseware")){
            if (!coursewareSuffixes.contains(suffix))
                throw new UnsupportedFileTypeException();
        }else{
            if (!imageSuffixes.contains(suffix))
                throw new UnsupportedFileTypeException();
        }
        //获取子路径
        int dir1 = id.hashCode()&0xf;
        int dir2 = id.hashCode()&0xf0;

        String path = dir1+File.separator+dir2+File.separator+id+"_teacher."+suffix;
        //创建目录
        String catalog = null;
        if(type.equals("avatar"))
            catalog = avatarBaseUrl + path;
        else if(type.equals("courseImage"))
            catalog = courseImageBaseUrl + path;
        else if(type.equals("video"))
            catalog = courseVideoBaseUrl + path;
        else
            catalog = coursewareBaseUrl + path;
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


}
