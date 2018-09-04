package com.service;

import com.BaseTest;
import com.lv.entity.Area;

import com.lv.service.AreaService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaServiceTest extends BaseTest {
    @Autowired
    private AreaService areaService;
    @Test
    public void test(){
        List<Area> areaList=areaService.getAreaList();
        assertEquals(4,areaList.size());

    }
}
