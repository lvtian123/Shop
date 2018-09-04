package com.dao;

import com.BaseTest;
import com.lv.dao.AreaMapper;
import com.lv.entity.Area;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaDaoTest  extends BaseTest {
    @Autowired
    private AreaMapper areaMapper;



    @Test

    public  void testQ(){
        List<Area> areaList=areaMapper.queryArea();
        assertEquals(4,areaList.size());
    }
}