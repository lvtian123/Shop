package com.dao;

import com.BaseTest;
import com.lv.dao.ProductCategoryMapper;
import com.lv.dao.ProductMapper;

import com.lv.entity.ProductCategory;
import com.lv.service.ProductCategoryService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductCategoryTest extends BaseTest {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Test
    @Ignore
    public void ProductCategoryTest() {
        int shopId = 34;
        List<ProductCategory> productCategoryList = productCategoryMapper.queryProductCategory(shopId);
        System.out.println("总的分类数：" + productCategoryList.size());

    }

    @Test
    @Ignore
    public void deleteProductCategory() {
        int productCategoryId = 1;
        int shopid = 34;
        productCategoryMapper.deleteByProductCatgoryId(productCategoryId, shopid);
    }

    @Test
    public void batchInsertCategory() {
         ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryName("商品类别");
        productCategory.setPriority(1);
        productCategory.setCreateTime(new Date());
        productCategory.setShopId(34);
     ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setProductCategoryName("商品类别2");
        productCategory1.setPriority(1);
        productCategory1.setCreateTime(new Date());
        productCategory1.setShopId(34);
        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("商品类别3");
        productCategory2.setPriority(1);
        productCategory2.setCreateTime(new Date());
        productCategory2.setShopId(34);
        List<ProductCategory> productCategoryList=new ArrayList<ProductCategory>();
        productCategoryList.add(productCategory);
        productCategoryList.add(productCategory1);
        productCategoryList.add(productCategory2);
        int effectedNum=productCategoryMapper.batchInsertCategory(productCategoryList);
        assertEquals(3,effectedNum);




    }

}
