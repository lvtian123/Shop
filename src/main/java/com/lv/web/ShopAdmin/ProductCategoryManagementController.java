package com.lv.web.ShopAdmin;

import com.lv.dto.ProductCategoryExecution;
import com.lv.dto.Result;
import com.lv.entity.ProductCategory;
import com.lv.entity.Shop;
import com.lv.enums.ProductCategoryStateEnum;
import com.lv.exception.ProductCategoryOperationException;
import com.lv.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/shopadmin")
public class ProductCategoryManagementController {


    @Autowired
    private ProductCategoryService productCategoryService;


    //获取产品分类列表
    @RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {
        //因为没有做登陆页面，所以暂时自定义shopid
       /* Shop shop=new Shop();
        shop.setShopId(34);
        request.getSession().setAttribute("currentShop",shop);*/

        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        Integer shopId=currentShop.getShopId();
        List<ProductCategory> productCategoryList = null;

        if (shopId != null && shopId > 0) {
            productCategoryList = productCategoryService.queryProductCategory(shopId);
            return new Result<List<ProductCategory>>(true, productCategoryList);
        } else {
            ProductCategoryStateEnum pcs = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<List<ProductCategory>>(false, pcs.getStateInfo(), pcs.getState());
        }

    }

    @RequestMapping(value = "/deleteproductcategorybyid", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteProductCategoryById(HttpServletRequest request, Integer productCategoryId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        ProductCategoryExecution pe = new ProductCategoryExecution();
        if (productCategoryId != null && productCategoryId > 0) {
            try {
                Shop currentShop = (Shop) request.getSession().getAttribute(
                        "currentShop");
                pe = productCategoryService.deleteProductCategoryById(productCategoryId, currentShop.getShopId());
                if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            } catch (RuntimeException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请至少选择一个商品类别");
        }
        return modelMap;
    }

    @RequestMapping(value = "/batchaddproductcategory", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> batchAddProductCategory(HttpServletRequest request, @RequestBody List<ProductCategory> productCategoryList) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        //ProductCategoryExecution pe = new ProductCategoryExecution();
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        for (ProductCategory productCategory : productCategoryList) {
            productCategory.setShopId(currentShop.getShopId());
        }
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                ProductCategoryExecution pe = productCategoryService.batchInsertProductCategory(productCategoryList);
                if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            } catch (ProductCategoryOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请至少输入一家店铺类别！");
        }
        return modelMap;
    }


}
