package com.lv.service.impl;

import com.lv.dao.ProductCategoryMapper;
import com.lv.dto.ProductCategoryExecution;
import com.lv.entity.ProductCategory;
import com.lv.enums.ProductCategoryStateEnum;
import com.lv.exception.ProductCategoryOperationException;
import com.lv.service.ProductCategoryService;
import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<ProductCategory> queryProductCategory(Integer shopId) {
        return productCategoryMapper.queryProductCategory(shopId);
    }

    @Override
    @Transactional //存在事务，一起提交才能回滚
    public ProductCategoryExecution deleteProductCategoryById(int productCategoryId, int shopId) throws ProductCategoryOperationException {
        try {
            int effectedNum = productCategoryMapper.deleteByProductCatgoryId(productCategoryId, shopId);
            if (effectedNum <= 0) {
                throw new ProductCategoryOperationException("商铺类别删除失败！");
            } else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("商铺类别删除失败!"+e.getMessage());
        }
    }

    @Override
    public ProductCategoryExecution batchInsertProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException {
        if(productCategoryList!=null&&productCategoryList.size()>0){
            try{
                int effectedNum=productCategoryMapper.batchInsertCategory(productCategoryList);
                if(effectedNum>0){
                    return  new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }else {
                    throw  new ProductCategoryOperationException("批量创建失败！");
                }

            }catch (Exception e){
                throw new ProductCategoryOperationException("batchAddProductCategoryError"+e.getMessage());
            }
        }
        return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);



    }


}
