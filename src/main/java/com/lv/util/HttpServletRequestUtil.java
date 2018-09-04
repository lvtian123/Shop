package com.lv.util;


import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil {

    public static int getInt(HttpServletRequest request, String key){
            try{
                return  Integer.decode(request.getParameter(key));
            }catch (Exception e){
                return  -1;
            }
    }
    public static long getLong(HttpServletRequest request, String key){
        try{
            return  Long.valueOf(request.getParameter(key));
        }catch (Exception e){
            return  -1;
        }
    }

    public static double getDouble(HttpServletRequest request, String key){
        try{
            return  Double.valueOf(request.getParameter(key));
        }catch (Exception e){
            return  -1d;
        }
    }

    public static boolean getBoolean(HttpServletRequest request, String key){
        try{
            return  Boolean.valueOf(request.getParameter(key));
        }catch (Exception e){
            return  false;
        }
    }
    public static String getString(HttpServletRequest request, String name){

        try{
            String result=request.getParameter(name);
            if(result!=null){
                result=result.trim();//去掉两边空格
            }
            if("".equals(result)){
                result= null;
            }
        return  result;
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
