package com.lv.enums;

public enum ProductStateEnum {



    CHECK(0, "下架"), OFFLINE(-1, "非法商品"), SUCCESS(1, "展示状态"),  INNER_ERROR(-1001, "操作失败"),
    NULL_PRODUCTID(-1002, "ShopId为空"), NULL_PRODUCT_INFO(-1003, "传入了空的信息"),EMPTY(-1004,"未传值");




    private int state;
    private String stateInfo;

    private ProductStateEnum(int state,String stateInfo){
        this.state=state;
        this.stateInfo=stateInfo;
    }

    public static ProductStateEnum  stateof(int state){
        for(ProductStateEnum productStateEnum:values()){
            if(productStateEnum.getState()==state){
                return productStateEnum;
            }
        }
        return null;
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
}
