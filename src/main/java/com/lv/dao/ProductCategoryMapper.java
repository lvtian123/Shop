package com.lv.dao;

import com.lv.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface ProductCategoryMapper {

    /*一个shopid对应多个productCategory*/
    List<ProductCategory> queryProductCategory(Integer shopId);

    /*根据shopId删除商品分类*/
    int deleteByProductCatgoryId(@Param("productCategoryId") int productCategoryId,@Param("shopId") int shopId);

    /*批量添加商品类别*/
    int batchInsertCategory(List<ProductCategory> productCategoryList);


    int insert(ProductCategory record);

    int insertSelective(ProductCategory record);

    ProductCategory selectByPrimaryKey(Integer productCategoryId);

    int updateByPrimaryKeySelective(ProductCategory record);

    int updateByPrimaryKey(ProductCategory record);


}