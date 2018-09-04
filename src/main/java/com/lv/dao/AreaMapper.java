package com.lv.dao;

import com.lv.entity.Area;

import java.util.List;

public interface AreaMapper {
    int deleteByPrimaryKey(Integer areaId);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer areaId);

    List<Area> queryArea();

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
}