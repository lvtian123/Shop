package com.lv.exception;

public class ProductCategoryOperationException extends RuntimeException {
    private static final long serialVersionUID = 2035876793333766540L;


     public ProductCategoryOperationException(String msg){
         super(msg);
     }
}
