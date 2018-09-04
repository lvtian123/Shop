package com.dao;

import com.BaseTest;
import com.lv.dao.ProductMapper;
import com.lv.entity.Product;
import com.lv.entity.ProductCategory;
import com.lv.entity.Shop;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class productTest extends BaseTest {
    @Autowired
    private ProductMapper productMapper;

    @Test
    @Ignore
    public void insertProductTest(){

        Product product=new Product();

        ProductCategory productCategory=new ProductCategory();
        productCategory.setProductCategoryId(32);

        Shop shop=new Shop();
        shop.setShopId(34);

        product.setProductName("脱毛膏");
        product.setProductDesc("用了还想用");
        product.setImgAddr("test1");
        product.setCreateTime(new Date());
        product.setEnableStatus(1);
        product.setLastEditTime(new Date());
        product.setPriority(10);
        product.setNormalPrice("30");
        product.setPromotionPrice("20");
        product.setProductCategory(productCategory);
        product.setShop(shop);
        int effectedNum=productMapper.insertProduct(product);
        assertEquals(1,effectedNum);

    }

    @Test
    @Ignore
    public void queryProductListTest(){


        Product productCondition = new Product();
        List<Product> productList = productMapper.queryProductList(productCondition, 0, 3);
        System.out.println("该页查询的数据：" + productList.size());
        int count=productMapper.queryProductCount(productCondition);
        assertEquals(1,1);

        //使用商品名进行模糊查询
        productCondition.setProductName("脱毛");
        productList = productMapper.queryProductList(productCondition, 0, 3);
        assertEquals(1,productList.size());
        count=productMapper.queryProductCount(productCondition);
        assertEquals(1,count);

    }














}
