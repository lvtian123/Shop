package com.service;

import com.BaseTest;
import com.lv.dto.ImageHodler;
import com.lv.dto.ShopExecution;
import com.lv.entity.Area;
import com.lv.entity.PersonInfo;
import com.lv.entity.Shop;
import com.lv.entity.ShopCategory;
import com.lv.enums.ShopStateEnum;
import com.lv.service.ShopCategoryService;
import com.lv.service.ShopService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;



    @Test
    public void testQ(){

        List<ShopCategory> shopCategoryList=shopCategoryService.getShopCategoryList(new ShopCategory());
        System.out.println(shopCategoryList.size());


    }







    @Test
    @Ignore
    public void testQueryShopListCount(){
        Shop shop=new Shop();
        Shop shopCondition=new Shop();

        PersonInfo owner=new PersonInfo();
        owner.setUserId(11);
        shopCondition.setOwner(owner);
        ShopExecution se= shopService.getShopList(shopCondition,2,2);
        System.out.println("列表数："+se.getShopList().size());
        System.out.println("总数："+se.getCount());



    }

    @Test
    @Ignore
    public  void updateTest() throws FileNotFoundException {
        Shop shop=shopService.queryById(34);
        shop.setShopName("修改后的名字");
        File shopImg=new File("C:/Users/lvtiantian/Desktop/image/1531841455.png");
        InputStream is= new FileInputStream(shopImg);
        ImageHodler imageHodler=new ImageHodler(is,"hello.png");
        ShopExecution shopExecution=shopService.modifyShop(shop,imageHodler);
        System.out.println("修改后图片的地址："+shopExecution.getShop().getShopImg());
    }

    @Test
    @Ignore
    public void test() throws FileNotFoundException {

        Shop shop = new Shop();
        PersonInfo owner=new PersonInfo();
        Area area=new Area();
        ShopCategory shopCategory=new ShopCategory();
        shop.setShopId(2);
        shop.setAdvice("审核中");
        area.setAreaId(3);
        shop.setArea(area);
        owner.setUserId(11);
        shop.setOwner(owner);
        shopCategory.setShopCategoryId(1);
        shop.setShopCategory(shopCategory);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setShopAddr("北苑2栋5楼");
        shop.setPriority(100);
        shop.setPhone("123456789");
        shop.setShopName("二杆子企业");
        shop.setShopDesc("受妹子");


        File shopImg=new File("C:/Users/lvtiantian/Desktop/image/2017060620114126875new.jpg");
        InputStream is= new FileInputStream(shopImg);
        ImageHodler imageHodler=new ImageHodler(is,shopImg.getName());
        ShopExecution se = shopService.addShop(shop,imageHodler);

        assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
    }
}
