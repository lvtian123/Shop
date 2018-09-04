package com.dao;

import com.lv.dao.HeadLineMapper;
import com.lv.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class HeadLineTest {

    @Autowired
    private HeadLineMapper headLineMapper;

    @Test
    public void test(){
        List<HeadLine> headLineList=headLineMapper.headLineList(new HeadLine());
        assertEquals(4,headLineList.size());
    }
}
