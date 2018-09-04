package com.lv.util;

public class PageUtil {
    public  static int calculateRowIndex(int pageIndex,int pageSize){
        return  (pageIndex>0)?(pageIndex-1)*pageSize:0;
        //pageIndex代表当前页数,pageSize代表每页的数据个数，
        //startIndex=(pageIndex-1)*pageSize ;   其中startIndex代表开始数据个数
        //startIndex:0   pageIndex=1  pageSize=10  初始参数
        //第一页：0-10 (不包括10，10个数据)  第二页：10-20(不包括20，10个数据)
    }
}
