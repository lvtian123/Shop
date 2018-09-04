package com.lv.dao;

import com.lv.entity.ProductImg;

import java.util.List;

public interface ProductImgMapper {




    /*批量添加商品类别*/
    int batchInsertProductImg(List<ProductImg> productImgs);

    List<ProductImg> queryProductImg(Integer productId);

    int deleteProductImgByProductId(Integer productId);








    int insert(ProductImg record);

    int insertSelective(ProductImg record);

    ProductImg selectByPrimaryKey(Integer productImgId);

    int updateByPrimaryKeySelective(ProductImg record);

    int updateByPrimaryKey(ProductImg record);
}