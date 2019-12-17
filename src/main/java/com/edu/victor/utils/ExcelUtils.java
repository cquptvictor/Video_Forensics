package com.edu.victor.utils;

import com.edu.victor.Exception.StuNumNotFound;
import com.edu.victor.domain.Student;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
/**
 * Excel文件的第一行为题目
 * 只能导入姓名、学号、性别三个参数
 * */
public class ExcelUtils {
    public static File convertToFile(MultipartFile file){
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
    public static List<Student> excelToStudent(MultipartFile multipartFile) throws StuNumNotFound {
        List<Student> list = new ArrayList<>();
       // File file = convertToFile(multipartFile);
        try{
            Workbook wb = new XSSFWorkbook(multipartFile.getInputStream());
            /**获取总行，总列数，分析位置*/
            Sheet sheet = wb.getSheetAt(0);
            int totalRows = sheet.getLastRowNum();
            Row hssfRow = sheet.getRow(0);
            int totalcolumns = hssfRow.getLastCellNum();
            Map<Integer,String> map = new HashMap<>();
            int stuNum = -1;
            int sexual = -1;
            int name = -1;
            for(int i = 0; i < totalcolumns; i++){
                String column = hssfRow.getCell(i).getStringCellValue();
                if(column.equals("学号")){
                    stuNum = i;
                }else if(column.equals("性别")){
                    sexual = i;
                }else if(column.equals("姓名")){
                    name = i;
                }
            }
            /**Excel中没有学号列，抛出错误*/
            if(stuNum == -1){
                throw new StuNumNotFound();
            }
            for(int i = 1; i <= totalRows; i++){
                Student student = new Student();
                hssfRow = sheet.getRow(i);
                Cell cell = hssfRow.getCell(stuNum);
                cell.setCellType(CellType.STRING);
                student.setUsername(hssfRow.getCell(stuNum).getStringCellValue());
                student.setPassword(hssfRow.getCell(stuNum).getStringCellValue());
                if(sexual != -1)
                    student.setSexual(hssfRow.getCell(sexual).getStringCellValue());
                if(name != -1)
                    student.setName(hssfRow.getCell(name).getStringCellValue());
                list.add(student);
            }
        }catch (IOException e){

        }
        return list;
    }
   private static Workbook createWorkBook(MultipartFile file) throws IOException, InvalidFormatException {
        String name = file.getOriginalFilename();
        String kind = name.split("\\.")[1];
        if(kind.equals("xlsx"))
            return new XSSFWorkbook(file.getInputStream());
        else
            return new XSSFWorkbook(file.getInputStream());
    }
    public static void main(String[] args){
        System.out.print(Arrays.toString("asdfafds.xls".split("\\.")));
    }
}
