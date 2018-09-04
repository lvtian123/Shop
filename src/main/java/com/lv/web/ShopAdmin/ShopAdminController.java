package com.lv.web.ShopAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "shopadmin", method = RequestMethod.GET)
public class ShopAdminController {
    @RequestMapping(value = "/shopoperation")
    public String shopOperation() {
        return "shop/shopOperation";
    }

    @RequestMapping(value = "/shoplist")
    public String shopList() {
        return "shop/shoplist";
    }

    @RequestMapping(value = "/shopmanagement")
    public String shopManagement() {
        return "shop/shopmanagement";
    }

    @RequestMapping(value = "/getproductcategory")
    public String productCategory() {
        return "shop/productcategory";
    }

    @RequestMapping(value = "/productedit")
    public String productEdit() {
        return "shop/productedit";
    }

    @RequestMapping(value = "/productmanagement", method = RequestMethod.GET)
    public String productManagement() {
        return "shop/productmanagement";
    }

    @RequestMapping(value = "/addproducts")
    public String addProduct() {
        return "shop/productedit";
    }
    @RequestMapping(value = "headline")
    public String headLine(){
        return "headLine/index";
    }

    @RequestMapping(value = "/queryshopcategory")
    public String shopCategoryList(){
        return "shop/shopLine";
    }

    @RequestMapping(value = "/queryshopdetail")
    public String queryShopDetail(){
        return "shop/shopdetail";
    }
}
