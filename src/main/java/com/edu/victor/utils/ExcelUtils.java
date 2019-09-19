package com.edu.victor.utils;

import com.edu.victor.domain.StuSearch;
import com.edu.victor.domain.Student;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
    public static List<Student> excelToStudent(MultipartFile multipartFile){
        List<Student> list = new ArrayList<>();
        File file = converToFile(multipartFile);
        try{
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(file);
            HSSFWorkbook wb = new HSSFWorkbook(poifsFileSystem);
            /**获取总行，总列数，分析位置*/
            HSSFSheet sheet = wb.getSheetAt(0);
            int totalRows = sheet.getLastRowNum();
            HSSFRow hssfRow = sheet.getRow(0);
            int totalcolumns = hssfRow.getLastCellNum();
            Map<Integer,String> map = new HashMap<>();
            for(int i = 0; i < totalcolumns; i++){
                String column = hssfRow.getCell(i).getStringCellValue();
                if(column.equals("学号")){
                    map.put(i,"stuNum");
                }else if(column.equals("性别")){
                    map.put(i,"sexual");
                }else if(column.equals("姓名")){
                    map.put(i,"name");
                }
            }
            for(int i = 1; i < totalRows; i++){
                Student student = new Student();
                 hssfRow = sheet.getRow(i);
                for(int j = 0; j < totalcolumns; j++){
                    String value = hssfRow.getCell(j).getStringCellValue();
                    if(map.get(j).equals("stuNum")){
                     student.setUsername(value);
                     student.setPassword(value);
                 }else if(map.get(j).equals("name")){
                        student.setName(value);
                    }else if(map.get(j).equals("sexual")){
                        student.setSexual(value);
                    }
                }
                list.add(student);
            }
        }catch (IOException e){

        }
        return list;
    }
}
