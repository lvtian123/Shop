package com.lv.service.impl;

import com.lv.dao.HeadLineMapper;
import com.lv.entity.HeadLine;
import com.lv.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HeadLineImpl implements HeadLineService {
    @Autowired
    private HeadLineMapper headLineMapper;

    @Override
    public List<HeadLine> headLineList(HeadLine headLineCondition) {
        return headLineMapper.headLineList(headLineCondition);
    }
}
