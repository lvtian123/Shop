package com.lv.service;

import com.lv.dto.ImageHodler;
import com.lv.dto.ProductExecution;
import com.lv.dto.ShopExecution;
import com.lv.entity.Product;
import com.lv.exception.ProductOperationException;

import java.io.InputStream;
import java.util.List;

public interface ProductService {

    /*查询商品列表并分页，可输入条件有：商品名（模糊），商品状态，商铺id，商品类别
    * */

     ProductExecution getProductList(Product productCondition,int pageIndex,int pageSize);

     Product getProduct(Integer ProductId);

     ProductExecution modifyProduct(Product product,ImageHodler thumbnail,List<ImageHodler> thumbnailList)throws ProductOperationException;


     //优化代码,创建imageHodler类
     //ShopExecution addShop(Product product, InputStream thumbnail, String thumbnailName, List<InputStream> productImgList,List<String> productImgNameList) throws ProductOperationException;
     ProductExecution addProduct(Product product, ImageHodler thumbnail,  List<ImageHodler> thumbnailList) throws ProductOperationException;




}
