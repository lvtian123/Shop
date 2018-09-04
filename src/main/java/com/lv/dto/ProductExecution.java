package com.lv.dto;

import com.lv.entity.Product;
import com.lv.enums.ProductStateEnum;

import java.util.List;

public class ProductExecution {

    private int state;
    private String stateInfo;
    private int count;
    private Product product;
    private List<Product> productList;

    public ProductExecution(){}
    //1,失败的构造器 ，不返回product
    public ProductExecution(ProductStateEnum productStateEnum){
        this.state=productStateEnum.getState();
        this.stateInfo=productStateEnum.getStateInfo();
    }
    //2,成功的构造器 ，返回单个product
    public ProductExecution(ProductStateEnum productStateEnum,Product product){
        this.state=productStateEnum.getState();
        this.stateInfo=productStateEnum.getStateInfo();
        this.product=product;
    }
    //3,成功的构造器 ，返回product集合
    public ProductExecution(ProductStateEnum productStateEnum,List<Product> productList ){
        this.state=productStateEnum.getState();
        this.stateInfo=productStateEnum.getStateInfo();
        this.productList=productList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
