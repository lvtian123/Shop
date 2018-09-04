package com.lv.dao;

import com.lv.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {



   List<Product> queryProductList(@Param("productCondition") Product productCondition,@Param("rowIndex") int rowIndex,
                                  @Param("pageSize")int pageSize) ;

   Product queryProductByProductId(Integer productId);


   int queryProductCount(@Param("productCondition")Product productCondition);


   int insertProduct(Product product);


    int updateProduct(Product product);






    int deleteByPrimaryKey(Integer productId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}