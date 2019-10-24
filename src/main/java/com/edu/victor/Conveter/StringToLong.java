package com.edu.victor.Conveter;

import com.edu.victor.Exception.WrongArgumentsException;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据绑定时将字符串转化为Date
 * */
public class StringToLong implements Converter<String, Long> {
    @Override
    public Long convert(String s) {
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date target = simpleDateFormat.parse(s);
            System.out.println(target.getTime());
            return target.getTime();
        }catch (Exception e){
            throw new WrongArgumentsException();
        }

    }
}
