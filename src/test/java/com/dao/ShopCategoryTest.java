package com.dao;

import com.BaseTest;
import com.lv.dao.ShopCategoryMapper;
import com.lv.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopCategoryTest extends BaseTest {
    @Autowired
    private ShopCategoryMapper shopCategoryMapper;
    @Test
    public void test(){
        List<ShopCategory> shopCategoryList=shopCategoryMapper.queryShopCategory(null);
        for(ShopCategory shopCategory: shopCategoryList){
            System.out.println(shopCategory);
        }
    }
}
