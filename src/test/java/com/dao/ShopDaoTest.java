package com.dao;

import com.BaseTest;
import com.lv.dao.AreaMapper;
import com.lv.dao.ShopMapper;

import com.lv.entity.Area;
import com.lv.entity.PersonInfo;
import com.lv.entity.Shop;

import com.lv.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private AreaMapper areaMapper;

    @Test
    public void testQueryShopListCount() {
        Shop shopCondition = new Shop();
        ShopCategory shopCategory=new ShopCategory();
        shopCategory.setShopCategoryId(1);
        Area area=new Area();
        area.setAreaId(3);
        ShopCategory zCategory = new ShopCategory();
        ShopCategory fCategory = new ShopCategory();
        fCategory.setShopCategoryId(2);
        zCategory.setParent(fCategory);
        shopCondition.setShopCategory(zCategory);
        shopCondition.setEnableStatus(1);
       /* shopCondition.setArea(area);*/
        shopCondition.setShopCategory(shopCategory);
        List<Shop> shopList=shopMapper.queryShopList(shopCondition,0,10);
        System.out.println(shopList.size());





        /*PersonInfo owner = new PersonInfo();
        owner.setUserId(11);
        shopCondition.setOwner(owner);
        List<Shop> shopList = shopMapper.queryShopList(shopCondition, 0, 3);
        System.out.println("该页查询的数据：" + shopList.size());
        int count = shopMapper.queryShopCount(shopCondition);
        System.out.println("该页查询的数据的总数:" + count);
        shopCategory.setShopCategoryId(1);
        shopCondition.setShopCategory(shopCategory);
        List<Shop> shopList2 = shopMapper.queryShopList(shopCondition, 0, 2);
        System.out.println("该页查询的数据总数" + shopList2.size());
        int count2 = shopMapper.queryShopCount(shopCondition);
        System.out.println("该页查询的数据的总数:" + count2);*/

    }

    @Test
    @Ignore
    public void quertShopByIdTest() {
        int shopId = 34;
        Shop shop = shopMapper.queryShopById(shopId);
        System.out.println(shop.getArea().getAreaId());
        System.out.println(shop.getArea().getAreaName());
    }


    @Test
    @Ignore
    public void shopInsert() {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();

        shop.setAdvice("吃的好");
        area.setAreaId(3);
        shop.setArea(area);
        owner.setUserId(11);
        shop.setOwner(owner);
        shopCategory.setShopCategoryId(1);
        shop.setShopCategory(shopCategory);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(1);
        shop.setShopAddr("北苑2栋5楼");
        shop.setPriority(100);
        shop.setShopImg("/upload/images/item/shop/15/2017060522042982266.png");
        shop.setPhone("123456789");
        shop.setShopName("大唐");
        shop.setShopDesc("收破烂");
        int effectedNum = shopMapper.insertShop(shop);
        assertEquals(1, effectedNum);

    }

}
