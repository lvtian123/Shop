package com.service;

import com.BaseTest;
import com.lv.dto.ImageHodler;
import com.lv.dto.ProductExecution;
import com.lv.entity.Product;
import com.lv.entity.ProductCategory;
import com.lv.entity.Shop;
import com.lv.enums.ProductStateEnum;
import com.lv.service.ProductService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.dc.pr.PRError;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductServiceTest extends BaseTest {
    @Autowired
    private ProductService productService;
    @Test
    @Ignore
    public void test(){
        Product productCondition=new Product();
        Shop shop =new Shop();
        shop.setShopId(34);
        productCondition.setShop(shop);

         ProductExecution productExecution =  productService.getProductList(productCondition,1,99);
         List<Product> productList=productExecution.getProductList();
         assertEquals(1,productList.size());
    }


    @Test
    @Ignore
    public void testAdd() throws FileNotFoundException {

        Shop shop=new Shop();
        ProductCategory productCategory=new ProductCategory();
        shop.setShopId(35);
        productCategory.setProductCategoryId(21);
        Product product=new Product();
        product.setShop(shop);
        product.setProductCategory(productCategory);
        product.setProductName("上古贴");
        product.setCreateTime(new Date());
        product.setLastEditTime(new Date());
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
        product.setPriority(10);

        //创建缩略图文件流
        File thumbnailFile=new File("C:/Users/lvtiantian/Desktop/image/okone.jpg");
        InputStream is=new FileInputStream(thumbnailFile);
        ImageHodler thumbnail=new ImageHodler(is, thumbnailFile.getName());
        //创建详情图
        File productImage1=new File("C:/Users/lvtiantian/Desktop/image/okthree.jpg");
        InputStream is1=new FileInputStream(productImage1);
        File productImage2=new File("C:/Users/lvtiantian/Desktop/image/oktwo.jpg");
        InputStream is2=new FileInputStream(productImage2);
        List<ImageHodler> productImageHodlerList=new ArrayList<ImageHodler>();
        productImageHodlerList.add(new ImageHodler(is1, productImage1.getName()));
        productImageHodlerList.add(new ImageHodler(is2, productImage2.getName()));
        //添加商品并验证；
        ProductExecution pe=productService.addProduct(product,thumbnail,productImageHodlerList);
        assertEquals(ProductStateEnum.SUCCESS.getState(),pe.getState());

    }

    @Test
    public void testDelete() throws FileNotFoundException {

    Shop shop=new Shop();
    ProductCategory productCategory=new ProductCategory();
    shop.setShopId(35);
    productCategory.setProductCategoryId(21);
    Product product=new Product();
    product.setProductId(3);
    product.setPriority(100);
    product.setShop(shop);
    product.setProductCategory(productCategory);
    product.setProductName("正式商品");
    product.setEnableStatus(1);
    //创建缩略图文件流
    File thumbnailFile=new File("C:/Users/lvtiantian/Desktop/image/okone.jpg");
    InputStream is= new FileInputStream(thumbnailFile);
    ImageHodler thumbnail=new ImageHodler(is,thumbnailFile.getName());
    //创建商品详情文件流
    File productImg1=new File("C:/Users/lvtiantian/Desktop/image/okone.jpg");
    InputStream is1=new FileInputStream(productImg1);
    File productImg2=new File("C:/Users/lvtiantian/Desktop/image/okthree.jpg");
    InputStream is2=new FileInputStream(productImg2);
    List<ImageHodler> imageHodlerList=new ArrayList<ImageHodler>();
    imageHodlerList.add(new ImageHodler(is1, productImg1.getName()));
    imageHodlerList.add(new ImageHodler(is2,productImg2.getName()));
    ProductExecution pe =productService.modifyProduct(product,thumbnail,imageHodlerList);
    System.out.println("sadsdasd");
    assertEquals(pe.getState(),ProductStateEnum.SUCCESS.getState());
}










}
