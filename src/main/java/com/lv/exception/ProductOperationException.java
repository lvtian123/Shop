package com.lv.exception;

public class ProductOperationException extends RuntimeException {

        private static final long serialVersionUID = 2035876793333766540L;

        public  ProductOperationException(String msg){
            super(msg);
        }
    }


