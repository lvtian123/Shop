package com.service;

import com.BaseTest;
import com.lv.entity.HeadLine;
import com.lv.service.HeadLineService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;



public class HeadLineTest extends BaseTest {
    @Autowired
    private HeadLineService headLineService;
    @Test
    public void queryHeadLine(){
        List<HeadLine> headLineList=headLineService.headLineList(new HeadLine());
       for(HeadLine headLine:headLineList){
           System.out.println(headLine);
       }
    }
}
