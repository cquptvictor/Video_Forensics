package com.edu.victor.utils;

import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.domain.Courseware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 处理video、avatar、courseImage、courseware的上传*/
public class UploadUtils {
    private static String avatarBaseUrl = "E:\\netClass\\avatar\\";
    private static String courseImageBaseUrl = "E:\\netClass\\course\\";
    private static String courseVideoBaseUrl = "E:\\netClass\\video\\";
    private static String coursewareBaseUrl = "E:\\netClass\\courseware\\";
    private static String defaultAvatar = "default.jpg";
    /*private static String avatarBaseUrl = "/root/netClass/avatar/";
    private static String courseImageBaseUrl = "/root/netClass/course/";
    private static String courseVideoBaseUrl = "/root/netClass/video/";
    private static String coursewareBaseUrl = "/root/netClass/courseware/";*/
    private static List<String> imageSuffixes = new ArrayList<>();
    private static List<String> videoSuffixes = new ArrayList<>();
    private static List<String> coursewareSuffixes = new ArrayList<>();
    /**添加类型限制*/
    static {
        imageSuffixes.add("jpg");
        imageSuffixes.add("png");
        videoSuffixes.add("mp4");
        coursewareSuffixes.add("pptx");
        coursewareSuffixes.add("ppt");
    }
    /**更新头像*/
    public static String updateAvatar(MultipartFile multipartFile, String url) throws IOException, UnsupportedFileTypeException {
        /**更新头像，直接替换；第一次上传还要创建文件*/
        if(!url.equals(defaultAvatar)){
            File file = new File(avatarBaseUrl+url);
            multipartFile.transferTo(file);
            return url;
        }else{
            String fileName = multipartFile.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (!imageSuffixes.contains(suffix))
                throw new UnsupportedFileTypeException();
            int dir1 = fileName.hashCode()&0xf;
            int dir2 = fileName.hashCode()&0xf0;
            String path = dir1 + File.separator + dir2 + File.separator + new Date().getTime()  + "." + suffix;
            String catalog = avatarBaseUrl + path;
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
    /**批量上传课件*/
    public static List<Courseware> multiSaveCourseware(MultipartFile[] multipartFiles,int teaId,int superiorId) throws UnsupportedFileTypeException {
        List<Courseware> coursewares = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles){
            Courseware courseware = new Courseware();
            //验证文件类型
            String fileName = multipartFile.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            fileName = fileName.split("\\.")[0];
            if (!coursewareSuffixes.contains(suffix))
                throw new UnsupportedFileTypeException();
            //获取子路径
            int dir1 = fileName.hashCode()&0xf;
            int dir2 = fileName.hashCode()&0xf0;
            String path = dir1 + File.separator + dir2 + File.separator + new Date().getTime() + "." + suffix;
            String catalog = coursewareBaseUrl + path;
            File catalogFile = new File(catalog);
            if(!catalogFile.getParentFile().exists()){
                catalogFile.mkdirs();
            }
            try {
                multipartFile.transferTo(catalogFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            courseware.setUrl(path);
            courseware.setTitle(fileName);
            courseware.setTeaId(teaId);
            courseware.setSuperiorId(superiorId);
            coursewares.add(courseware);
        }
        return coursewares;
    }
    public static String saveImage(MultipartFile multipartFile,String type) throws UnsupportedFileTypeException {
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
        int dir1 = fileName.hashCode()&0xf;
        int dir2 = fileName.hashCode()&0xf0;

        String path = null;
        /**头像是可以覆盖的，课程图片、视频、课件要避免覆盖*/
        String catalog = null;
        if(type.equals("courseImage")) {
            path = dir1 + File.separator + dir2 + File.separator + new Date().getTime() + "." + suffix;
            catalog = courseImageBaseUrl + path;
        }
        else if(type.equals("video"))
        {
            path = dir1 + File.separator + dir2 + File.separator + new Date().getTime() + "_" + suffix;
            catalog = courseVideoBaseUrl +  path;
        }/*else if(type.equals("courseware")){
            path = dir1 + File.separator + dir2 + File.separator + new Date().getTime() + "_" + id + "." + suffix;
            catalog = coursewareBaseUrl + path;
        }*/


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
    /**更新课程的展示图片*/
    public static void updateCourseImage(MultipartFile multipartFile,String url){
        File catalogFile = new File(courseImageBaseUrl + url);
        try {
            multipartFile.transferTo(catalogFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**批量删除课件和课程视频*/
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
    /**单个删除课件和视频*/
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
