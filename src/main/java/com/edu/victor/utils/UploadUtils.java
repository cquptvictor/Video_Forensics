package com.edu.victor.utils;

import com.edu.victor.Exception.UnsupportedFileTypeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 处理video、avatar、courseImage、courseware的上传*/
public class UploadUtils {
    /*private static String avatarBaseUrl = "E:\\netClass\\avatar\\";
    private static String courseImageBaseUrl = "E:\\netClass\\course\\";
    private static String courseVideoBaseUrl = "E:\\netClass\\video\\";
    private static String coursewareBaseUrl = "E:\\netClass\\courseware\\";
    */
    private static String avatarBaseUrl = "/root/netClass/avatar/";
    private static String courseImageBaseUrl = "/root/netClass/course/";
    private static String courseVideoBaseUrl = "/root/netClass/video/";
    private static String coursewareBaseUrl = "/root/netClass/courseware/";
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

        String path = null;
        /**头像是可以覆盖的，课程图片、视频、课件要避免覆盖*/
        String catalog = null;
        if(type.equals("avatar")) {
            path = dir1 + File.separator + dir2 + File.separator + id + "_teacher." + suffix;
            catalog = avatarBaseUrl + path;
        }else if(type.equals("courseImage")) {
            path = dir1 + File.separator + dir2 + File.separator + new Date().getTime() + "_" + id + "." + suffix;
            catalog = courseImageBaseUrl + path;
        }
        else if(type.equals("video"))
        {
            path = dir1 + File.separator + dir2 + File.separator + new Date().getTime() + "_" + id + "." + suffix;
            catalog = courseVideoBaseUrl +  path;
        }else if(type.equals("courseware")){
            path = dir1 + File.separator + dir2 + File.separator + new Date().getTime() + "_" + id + "." + suffix;
            catalog = coursewareBaseUrl + path;
        }


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
    public static void updateCourseImage(MultipartFile multipartFile,String url){
        File catalogFile = new File(courseImageBaseUrl + url);
        try {
            multipartFile.transferTo(catalogFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void deleteFile(List<String> url,String type){
        String base = null;
        if(type.equals("video")){
            base = courseVideoBaseUrl ;
        }else
            base = coursewareBaseUrl ;
        for(String each:url) {
            File file = new File(base,each);
            file.delete();
        }
    }
    public static void deleteFile(String url,String type){
        String base = null;
        if(type.equals("video")){
            base = courseVideoBaseUrl ;
        }else
            base = coursewareBaseUrl ;
            File file = new File(base,url);
            file.delete();
    }
}
