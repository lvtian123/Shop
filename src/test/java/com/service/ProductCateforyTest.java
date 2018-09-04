package com.service;

import com.BaseTest;
import com.lv.entity.ProductCategory;
import com.lv.service.ProductCategoryService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductCateforyTest  extends BaseTest {
    @Autowired
    private ProductCategoryService productCategoryService;
    @Test
    @Ignore
    public void  ProductCateforyTest(){
        int shopId=34;
        List<ProductCategory> productCategoryList=productCategoryService.queryProductCategory(shopId);
        System.out.println("产品类别数量:"+productCategoryList.size());
    }
   @Test
    public void deleteProductCategory(){
        int productCategoryId=1;
        int shopId=34;
        productCategoryService.deleteProductCategoryById(productCategoryId,shopId);

    }

}
