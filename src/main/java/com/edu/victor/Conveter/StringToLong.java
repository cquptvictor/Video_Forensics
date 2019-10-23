package com.edu.victor.Conveter;

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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date target = simpleDateFormat.parse(s);
            return target.getTime();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
