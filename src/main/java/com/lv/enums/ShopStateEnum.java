package com.lv.enums;

public enum ShopStateEnum {

    CHECK(0, "审核中"), OFFLINE(-1, "非法商铺"), SUCCESS(1, "操作成功"), PASS(2, "通过认证"), INNER_ERROR(-1001, "操作失败"),
    NULL_SHOPID(-1002, "ShopId为空"), NULL_SHOP_INFO(-1003, "传入了空的信息");


    private int state;
    private String stateInfo;


    private ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }
    /*
     * 依据传入的state返回相应的enum;
     * 1.遍历ShopStateEnum,values包含所有的枚举值
     * 2.如果等于state返回
     * */

    public static ShopStateEnum stateOf(int state) {
        for (ShopStateEnum shopStateEnum : values()) {
            if (shopStateEnum.getState() == state) {
                return shopStateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }


}
