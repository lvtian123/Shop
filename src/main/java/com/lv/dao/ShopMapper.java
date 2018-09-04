package com.lv.dao;

import com.lv.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopMapper {

    //分页查询店铺，可输入的条件有：店铺名（模糊查询），店铺状态，店铺类别，区域id，owner

    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,@Param("rowIndex") int rowIndex,
                             @Param("pageSize")int pageSize);
    /*单页的数据总数*/
    int queryShopCount(@Param("shopCondition") Shop Condition);



    int insertShop(Shop shop);


    Shop queryShopById(Integer shopId);

    int updateShop(Shop record);


}