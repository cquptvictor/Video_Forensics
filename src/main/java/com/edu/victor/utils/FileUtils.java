package com.edu.victor.utils;

import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.Service.impl.DownloadFileNotFoundException;
import com.edu.victor.domain.Courseware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 处理video、avatar、courseImage、courseware的上传*/
public class FileUtils {
   /* private static String avatarBaseUrl = "E:\\netClass\\avatar\\";
    private static String courseImageBaseUrl = "E:\\netClass\\course\\";
    private static String courseVideoBaseUrl = "E:\\netClass\\video\\";
    private static String coursewareBaseUrl = "E:\\netClass\\courseware\\";
    private static String SubmittedHomeworkUrl = "E:\\netClass\\submitted\\";*/
    private static String defaultAvatar = "default.jpg";

/*    private static String avatarBaseUrl = "/root/netClass/avatar/";
    private static String courseImageBaseUrl = "/root/netClass/course/";
    private static String courseVideoBaseUrl = "/root/netClass/video/";
    private static String coursewareBaseUrl = "/root/netClass/courseware/";
    private static String SubmittedHomeworkUrl = "/root/netClass/submitted/";
*/
    private static String avatarBaseUrl = "/home/redis1/netClass/avatar/";
    private static String courseImageBaseUrl = "/home/redis1/netClass/course/";
    private static String courseVideoBaseUrl = "/home/redis1/netClass/video/";
    private static String coursewareBaseUrl = "/home/redis1/netClass/courseware/";
    private static String SubmittedHomeworkUrl = "/home/redis1/netClass/submitted/";
    private static List<String> imageSuffixes = new ArrayList<>();
    private static List<String> videoSuffixes = new ArrayList<>();
    private static List<String> coursewareSuffixes = new ArrayList<>();
    private static List<String> homeworkSUffixes = new ArrayList<>();
    /**添加类型限制*/
    static {
        imageSuffixes.add("jpg");
        imageSuffixes.add("png");
        videoSuffixes.add("mp4");
        coursewareSuffixes.add("pptx");
        coursewareSuffixes.add("ppt");
        homeworkSUffixes.add("pdf");
        homeworkSUffixes.add("word");
        homeworkSUffixes.add("jpg");
        homeworkSUffixes.add("png");
        homeworkSUffixes.add("txt");
    }
    private static String saveFile(MultipartFile multipartFile,String type) throws UnsupportedFileTypeException {
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        fileName = fileName.split("\\.")[0];
        int dir1 = fileName.hashCode()&0xf;
        int dir2 = fileName.hashCode()&0xf0;
        String path = dir1 + File.separator + dir2 + File.separator + new Date().getTime() + "." + suffix;
        String directory;
        if(type.equals("video")){
            if(!videoSuffixes.contains(suffix))
                throw new UnsupportedFileTypeException();
            directory = courseVideoBaseUrl + path;
        }else if(type.equals("courseware")){
            if (!coursewareSuffixes.contains(suffix))
                throw new UnsupportedFileTypeException();
            directory = coursewareBaseUrl + path;
        }else if (type.equals("courseImage")) {
            if(!imageSuffixes.contains(suffix))
                throw new UnsupportedFileTypeException();
            directory = courseImageBaseUrl + path;
        }else if (type.equals("avatar")){
            if (!imageSuffixes.contains(suffix))
                throw new UnsupportedFileTypeException();
            directory = avatarBaseUrl + path;
        }else if(type.equals("homework")){
            if(!homeworkSUffixes.contains(suffix))
                throw new UnsupportedFileTypeException();
            directory = SubmittedHomeworkUrl + path;
        }else{
            throw new UnsupportedFileTypeException();
        }
        File file = new File(directory);
        if(!file.getParentFile().exists()){
            file.mkdirs();
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return path;
    }
    /**更新头像*/
    public static String updateAvatar(MultipartFile multipartFile, String url) throws IOException, UnsupportedFileTypeException {
        /**更新头像，直接替换；第一次上传还要创建文件*/
        if(!url.equals(defaultAvatar)){
            File file = new File(avatarBaseUrl+url);
            multipartFile.transferTo(file);
            return url;
        }else{
            String path  = saveFile(multipartFile,"avatar");
            return path;
        }
    }
    /**批量上传课件*/
    public static List<Courseware> multiSaveCourseware(MultipartFile[] multipartFiles,int teaId,int superiorId) throws UnsupportedFileTypeException {
        List<Courseware> coursewares = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles){
            Courseware courseware = new Courseware();
            String fileName = multipartFile.getOriginalFilename();
            fileName = fileName.split("\\.")[0];

            String path = saveFile(multipartFile,"courseware");
            courseware.setUrl(path);
            courseware.setTitle(fileName);
            courseware.setTeaId(teaId);
            courseware.setSuperiorId(superiorId);
            coursewares.add(courseware);
        }
        return coursewares;
    }
    /**上传视频和课程图片*/
    public static String  saveImage(MultipartFile multipartFile,String type) throws UnsupportedFileTypeException {
        String path = saveFile(multipartFile,type);
        return path;
    }
    /**更新课程的展示图片*/
    public static void updateCourseImage(MultipartFile multipartFile,String url) throws UnsupportedFileTypeException {
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if(!imageSuffixes.contains(suffix))
            throw new UnsupportedFileTypeException();
        File catalogFile = new File(courseImageBaseUrl + url);
        try {
            multipartFile.transferTo(catalogFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**再次提交作业*/
    public static String reSubmit(MultipartFile multipartFile,String url) throws UnsupportedFileTypeException {
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        File fileToDelete = new File(SubmittedHomeworkUrl,url);
        fileToDelete.delete();
        //有可能两次上传的文件类型不同
        url = url.substring(0,url.lastIndexOf(".") + 1) + suffix;
        if(!homeworkSUffixes.contains(suffix))
            throw new UnsupportedFileTypeException();
        File catalogFile = new File(SubmittedHomeworkUrl + url);
        try {
            multipartFile.transferTo(catalogFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
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
    public static void updateSectionVideo(MultipartFile multipartFile,String url) throws UnsupportedFileTypeException {
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if(!videoSuffixes.contains(suffix))
            throw new UnsupportedFileTypeException();
        File catalogFile = new File(courseVideoBaseUrl + url);
        try {
            multipartFile.transferTo(catalogFile);
        } catch (IOException e) {
            e.printStackTrace();
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
    public static ResponseEntity<byte[]> dowmloadSubmittedHomework(String url) throws DownloadFileNotFoundException {
        url = SubmittedHomeworkUrl + url;
        File file = new File(url);
        byte[] body = null;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            body = new byte[is.available()];
            is.read(body);
        } catch (IOException e) {
            throw new DownloadFileNotFoundException();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + file.getName());
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<>(body, headers, statusCode);
        return entity;
    }
}
