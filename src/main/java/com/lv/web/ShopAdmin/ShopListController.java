package com.lv.web.ShopAdmin;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lv.dto.ShopExecution;
import com.lv.entity.Area;
import com.lv.entity.Shop;
import com.lv.entity.ShopCategory;
import com.lv.enums.ShopStateEnum;
import com.lv.exception.ShopOperationException;
import com.lv.service.AreaService;
import com.lv.service.ShopCategoryService;
import com.lv.service.ShopService;
import com.lv.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/shopadmin")
@Controller
public class ShopListController {

    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopService shopService;

    //获取要显示的shopCategory
    @RequestMapping("/queryshopcategorylist")
    @ResponseBody
    public Map<String, Object> queryShopCategoryList(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        int parentId = HttpServletRequestUtil.getInt(request, "parentId");
        List<ShopCategory> shopCategoryList = null;
        if (parentId != -1) {
            try {
                ShopCategory shopCategoryCondition = new ShopCategory();
                ShopCategory parent = new ShopCategory();
                parent.setShopCategoryId(parentId);
                shopCategoryCondition.setParent(parent);
                shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
            }
        } else {
            try {
                shopCategoryList = shopCategoryService
                        .getShopCategoryList(null);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
            }
        }
        modelMap.put("shopCategoryList", shopCategoryList);
        List<Area> areaList = null;
        try {
            areaList = areaService.getAreaList();
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
            return modelMap;
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
        }
        return modelMap;
    }

    //获取查询的条件
    @RequestMapping("/queryshoplist")
    @ResponseBody
    public Map<String, Object> queryShopList(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //获取页码
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        //获取每页数据的个数
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");

        if ((pageIndex > -1) && (pageSize > -1)) {
            //（组合相关条件）
            //获取区域相关
            int areaId = HttpServletRequestUtil.getInt(request, "areaId");
            //获取模糊查询相关
            String shopName = HttpServletRequestUtil.getString(request, "shopName");
            //获取一级类别id
            int shopCategoryId = HttpServletRequestUtil.getInt(request, "shopCategoryId");
            //获取二级类别id
            int parentId = HttpServletRequestUtil.getInt(request, "parentId");


            Shop shopCondition = queryCondition(parentId,
                    shopCategoryId, areaId, shopName);
            ShopExecution se = shopService.getShopList(shopCondition, pageIndex, pageSize);

            modelMap.put("shopList", se.getShopList());
            modelMap.put("count", se.getCount());
            modelMap.put("success", true);

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "pageIndex  or  pageSize error!");
        }
        return modelMap;
    }

    private Shop queryCondition(int parentId,
                                int shopCategoryId, int areaId, String shopName) {
        Shop shopCondition = new Shop();
        if (parentId != -1L) {
            ShopCategory cCategory = new ShopCategory();
            ShopCategory pCategory = new ShopCategory();
            pCategory.setShopCategoryId(parentId);
            cCategory.setParent(pCategory);
            shopCondition.setShopCategory(cCategory);
        }
        if (shopCategoryId != -1L) {
            ShopCategory shopCategory = new ShopCategory();
            shopCategory.setShopCategoryId(shopCategoryId);
            shopCondition.setShopCategory(shopCategory);
        }
        if (areaId != -1L) {
            Area area = new Area();
            area.setAreaId(areaId);
            shopCondition.setArea(area);
        }

        if (shopName != null) {
            shopCondition.setShopName(shopName);
        }
        shopCondition.setEnableStatus(1);
        return shopCondition;

    }
}
