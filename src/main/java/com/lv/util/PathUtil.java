package com.lv.util;

import java.text.SimpleDateFormat;
import java.util.Random;

public class PathUtil {

    private static String seperator = System.getProperty("file.separator");


    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "C:/Users/lvtiantian/Desktop/image/";
        } else {
            basePath = "/home/lvtian/image/";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    public static String getShopImagePath(int shopId) {
        String imagePath = "/upload/item/shop/" + shopId + "/";
        return imagePath.replace("/", seperator);
    }



}
