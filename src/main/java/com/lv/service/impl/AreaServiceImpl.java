package com.lv.service.impl;

import com.lv.dao.AreaMapper;
import com.lv.entity.Area;
import com.lv.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AreaServiceImpl  implements AreaService {
    @Autowired
    private AreaMapper areaMapper;
    @Override
    public List<Area> getAreaList() {
        return areaMapper.queryArea();
    }
}
