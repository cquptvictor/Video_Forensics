package com.edu.victor.utils;

import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.domain.Courseware;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
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

     private static String avatarBaseUrl = "/root/netClass/avatar/";
    private static String courseImageBaseUrl = "/root/netClass/course/";
    private static String courseVideoBaseUrl = "/root/netClass/video/";
    private static String coursewareBaseUrl = "/root/netClass/courseware/";
    private static String SubmittedHomeworkUrl = "/root/netClass/submitted/";
    private static String MarkdownImageBaseUrl = "/root/netClass/markdown/";
    private static String BookBaseUrl = "/root/netClass/book/";

   /* private static String avatarBaseUrl = "/home/redis1/netClass/avatar/";
    private static String courseImageBaseUrl = "/home/redis1/netClass/course/";
    private static String courseVideoBaseUrl = "/home/redis1/netClass/video/";
    private static String coursewareBaseUrl = "/home/redis1/netClass/courseware/";
    private static String SubmittedHomeworkUrl = "/home/redis1/netClass/submitted/";
    private static String MarkdownImageBaseUrl = "/root/netClass/markdown/";
 */   private static List<String> imageSuffixes = new ArrayList<>();
    private static List<String> videoSuffixes = new ArrayList<>();
    private static List<String> coursewareSuffixes = new ArrayList<>();
    private static List<String> homeworkSuffixes = new ArrayList<>();
    private static List<String> markDownSuffixes = new ArrayList<>();
    private static List<String> bookSuffixes = new ArrayList<>();

    /**添加类型限制*/
    static {
        imageSuffixes.add("jpg");
        imageSuffixes.add("png");
        imageSuffixes.add("jpeg");
        markDownSuffixes.addAll(imageSuffixes);

        videoSuffixes.add("mp4");

        coursewareSuffixes.add("pptx");
        coursewareSuffixes.add("ppt");
        coursewareSuffixes.add("docx");
        coursewareSuffixes.add("doc");
        coursewareSuffixes.add("pdf");
        coursewareSuffixes.add("md");


        homeworkSuffixes.add("pdf");
        homeworkSuffixes.add("docx");
        homeworkSuffixes.add("doc");
        homeworkSuffixes.add("jpg");
        homeworkSuffixes.add("png");
        homeworkSuffixes.add("jpeg");
        homeworkSuffixes.add("txt");

        bookSuffixes.add("pdf");
        bookSuffixes.add("txt");
        bookSuffixes.add("epub");
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
        }else if (type.equals("image")) {
            if(!imageSuffixes.contains(suffix))
                throw new UnsupportedFileTypeException();
            directory = courseImageBaseUrl + path;
        }else if (type.equals("avatar")){
            if (!imageSuffixes.contains(suffix))
                throw new UnsupportedFileTypeException();
            directory = avatarBaseUrl + path;
        }else if(type.equals("homework")){
            if(!homeworkSuffixes.contains(suffix))
                throw new UnsupportedFileTypeException();
            directory = SubmittedHomeworkUrl + path;
        }else if(type.equals("markdown")){
            if(!markDownSuffixes.contains(suffix))
                throw  new UnsupportedFileTypeException();
            directory = MarkdownImageBaseUrl + path;
        }else if(type.equals("book")){
            if(!bookSuffixes.contains(suffix))
                throw  new UnsupportedFileTypeException();
            directory = BookBaseUrl + path;
        } else{
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
        /**第一次换头像，需要创建文件这些
         * 后面换头像，直接覆盖文件*/
        String suffix = url.split("\\.")[1];
        if(!imageSuffixes.contains(suffix))
            throw new FileNotFoundException();
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
    public static List<Courseware> multiSaveCourseware(MultipartFile[] multipartFiles,int teaId,int superiorId) throws UnsupportedFileTypeException, FileNotFoundException {
        List<Courseware> coursewares = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles){
            Courseware courseware = new Courseware();
            String fileName = multipartFile.getOriginalFilename();
            String[] preAndSuf= fileName.split("\\.");
            fileName = preAndSuf[0];
            //验证文件类型
            String suffix = preAndSuf[1];
            if(!coursewareSuffixes.contains(suffix))
                throw new FileNotFoundException();
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
    
    /**再次提交作业和更新书*/
    public static String reSubmit(MultipartFile multipartFile,String url,String type) throws UnsupportedFileTypeException {
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        //先删除之前的文件,检查文件后缀
        File fileToDelete = null;
        String baseUrl = null;
        if(type.equals("homework")){
            if(!homeworkSuffixes.contains(suffix))
                throw new UnsupportedFileTypeException();
            fileToDelete = new File(SubmittedHomeworkUrl,url);
            baseUrl = SubmittedHomeworkUrl;
        }else{
            if(!bookSuffixes.contains(suffix))
                throw new UnsupportedFileTypeException();
            fileToDelete = new File(BookBaseUrl,url);
            baseUrl = BookBaseUrl;
        }
        fileToDelete.delete();
        //新的url
        url = url.substring(0,url.lastIndexOf(".") + 1)+suffix;
        File catalogFile = new File(baseUrl + url);
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
        }else if(type.equals("courseware")){
            base = coursewareBaseUrl ;
        }else
            base = SubmittedHomeworkUrl;
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
        }else if(type.equals("avatar")) {
            base = avatarBaseUrl;
        }else if(type.equals("book")){
             base = BookBaseUrl;
        }else if(type.equals("image")) {
            base = courseImageBaseUrl;
        }else
            base = coursewareBaseUrl;
        File file = new File(base,url);
        file.delete();
    }
  /*  public static ResponseEntity<byte[]> dowmloadSubmittedHomework(String url) throws DownloadFileNotFoundException {
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
        headers.add("Content-Disposition","attachment;filename=" + file.getName());
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<>(body, headers, statusCode);
        return entity;
    }*/
}
