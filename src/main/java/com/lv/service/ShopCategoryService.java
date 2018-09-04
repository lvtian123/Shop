package com.lv.service;

import com.lv.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryService {
   List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
