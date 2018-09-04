package com.dao;

import com.BaseTest;
import com.lv.dao.ProductImgMapper;
import com.lv.entity.ProductImg;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductImgTest extends BaseTest {
    @Autowired
    private ProductImgMapper productImgMapper;

    @Test
    @Ignore
    public void batchInsertProductImgTest(){

        ProductImg productImg=new ProductImg();
        productImg.setImgAddr("TEST1");
        productImg.setCreateTime(new Date());
        productImg.setImgDesc("真好看");
        productImg.setPriority(10);
        productImg.setProductId(2);
        ProductImg productImg1=new ProductImg();
        productImg1.setImgAddr("TEST2");
        productImg1.setCreateTime(new Date());
        productImg1.setImgDesc("真");
        productImg1.setPriority(9);
        productImg1.setProductId(2);
        List<ProductImg> productImgList=new ArrayList<ProductImg>();
        productImgList.add(productImg);
        productImgList.add(productImg1);
        int effectedNum=productImgMapper.batchInsertProductImg(productImgList);
        assertEquals(2,effectedNum);
    }

    @Test
    public void queryProductImgTest(){
        List<ProductImg> productImgList=productImgMapper.queryProductImg(2);
        System.out.println("productImgList"+productImgList.size());
    }
}
