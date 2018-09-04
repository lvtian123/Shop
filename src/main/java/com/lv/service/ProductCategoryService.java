package com.lv.service;

import com.lv.dto.ProductCategoryExecution;
import com.lv.entity.ProductCategory;
import com.lv.exception.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> queryProductCategory(Integer shopId);

    ProductCategoryExecution deleteProductCategoryById(int productCategoryId , int shopId)throws ProductCategoryOperationException;

    ProductCategoryExecution batchInsertProductCategory(List<ProductCategory> productCategoryList) throws  ProductCategoryOperationException;
}
