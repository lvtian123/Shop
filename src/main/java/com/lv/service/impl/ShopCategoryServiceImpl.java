package com.lv.service.impl;

import com.lv.dao.ShopCategoryMapper;
import com.lv.entity.ShopCategory;
import com.lv.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryMapper shopCategoryMapper;

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryMapper.queryShopCategory(shopCategoryCondition);
    }
}
