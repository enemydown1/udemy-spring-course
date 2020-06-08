package com.udemy.spring.course.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

    public static String decodeParameter(String string){
        try{
            return URLDecoder.decode(string, "UTF-8");
        }
        catch (UnsupportedEncodingException exception){
            return "";
        }
    }

    public static List<Integer> decodeIntList(String string){
        String[] vet = string.split(",");
        List<Integer> list = new ArrayList<>();
        for (String s : vet) {
            list.add(Integer.parseInt(s));
        }
        return list;
    }
}
