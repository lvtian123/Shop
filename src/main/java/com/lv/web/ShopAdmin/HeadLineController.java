package com.lv.web.ShopAdmin;

import com.lv.entity.HeadLine;
import com.lv.entity.ShopCategory;
import com.lv.service.HeadLineService;
import com.lv.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/headline")
@Controller
public class HeadLineController {

    @Autowired
    private HeadLineService headLineService;
    @Autowired
    private ShopCategoryService shopCategoryService;

    @RequestMapping(value = "/queryheadline", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> queryHeadLine() {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(1);
        List<HeadLine> headLineList = headLineService.headLineList(headLineCondition);
        try {
            if (headLineList != null) {


                modelMap.put("headLineList", headLineList);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "查询为空");
            }


        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "查询失败");
            return modelMap;
        }
        List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(null);
        try{
            if(shopCategoryList!=null){
                modelMap.put("shopCategoryList",shopCategoryList);
            }else {
                modelMap.put("errMsg","店铺分类为空");
            }
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("errMsg", "查询失败");
            return modelMap;
        }

        modelMap.put("success", true);
        return modelMap;

    }


}
