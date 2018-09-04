package com.lv.service;

import com.lv.dto.ImageHodler;
import com.lv.dto.ShopExecution;
import com.lv.entity.Shop;
import com.lv.exception.ShopOperationException;

import java.io.InputStream;


public interface ShopService {

    ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);

    Shop queryById(Integer shopId);

    ShopExecution modifyShop(Shop shop, ImageHodler thumbnail)throws ShopOperationException;

    ShopExecution addShop(Shop shop, ImageHodler thumbnail) throws ShopOperationException;
}
