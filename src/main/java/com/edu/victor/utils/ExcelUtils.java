package com.edu.victor.utils;

import com.edu.victor.domain.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ExcelUtils {
    public static File converToFile(MultipartFile file){
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件后缀
        String prefix=fileName.substring(fileName.lastIndexOf("."));
        // 用uuid作为文件名，防止生成的临时文件重复
        final File excelFile;
        try {
            excelFile = File.createTempFile(new Date().getTime()+"", prefix);
            file.transferTo(excelFile);
            return excelFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
            return null;
    }
    public static List<Student>
}
