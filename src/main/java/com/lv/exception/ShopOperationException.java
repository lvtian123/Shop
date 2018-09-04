package com.lv.exception;
//对RuntimeException封装
public class ShopOperationException extends RuntimeException {
    private static final long serialVersionUID = 2035876793333766540L;

    public  ShopOperationException(String msg){
        super(msg);
    }
}
