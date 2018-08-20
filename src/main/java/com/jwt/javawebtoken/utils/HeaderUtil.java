package com.jwt.javawebtoken.utils;

import javax.servlet.http.HttpServletRequest;

public final class HeaderUtil {

    public static String getHeader(HttpServletRequest request){
       return request.getHeader("Authorization");
    }

    public static String getTokenHeader(String fullToken){
        int index = fullToken.indexOf(" ");
        return fullToken.substring(index + 1 ,fullToken.length());
    }
}
