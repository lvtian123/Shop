package com.lv.service;

import com.lv.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeadLineService {

    List<HeadLine> headLineList(@Param("headLineCondition") HeadLine headLineCondition );

}
