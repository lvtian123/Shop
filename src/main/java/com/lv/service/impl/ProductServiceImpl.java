package com.lv.service.impl;

import com.lv.dao.ProductImgMapper;
import com.lv.dao.ProductMapper;
import com.lv.dto.ImageHodler;
import com.lv.dto.ProductExecution;
import com.lv.entity.Product;
import com.lv.entity.ProductImg;
import com.lv.enums.ProductStateEnum;
import com.lv.exception.ProductOperationException;
import com.lv.service.ProductService;
import com.lv.util.ImageUtil;
import com.lv.util.PageUtil;
import com.lv.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductImgMapper productImgMapper;

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        //页码转换成数据库的行码
        int rowIndex = PageUtil.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = productMapper.queryProductList(productCondition, rowIndex, pageSize);
        int count = productMapper.queryProductCount(productCondition);
        ProductExecution productExecution = new ProductExecution();
        if (productList != null) {
            productExecution.setProductList(productList);
            productExecution.setCount(count);
        } else {
            productExecution.setState(ProductStateEnum.NULL_PRODUCT_INFO.getState());
        }

        return productExecution;
    }

    @Override
    public Product getProduct(Integer ProductId) {
        return productMapper.queryProductByProductId(ProductId);
    }

    @Override
    @Transactional
    //若缩略图有值，删除缩略图，添加索洛图，获取缩略图相对路径并赋值给product
    //详情图片和缩略图一样处理
    //更新tb_product信息
    public ProductExecution modifyProduct(Product product, ImageHodler thumbnail, List<ImageHodler> thumbnailList) throws ProductOperationException {

        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            product.setLastEditTime(new Date());
            //删除原有的缩略图并添加新的缩略图
            if (thumbnail != null) {
                Product productTemp = productMapper.queryProductByProductId(product.getProductId());
                if (productTemp.getImgAddr() != null) {
                    ImageUtil.deleteFileOrPath(productTemp.getImgAddr());
                }
                addThumbnail(product, thumbnail);
            }
            if (thumbnailList != null && thumbnailList.size() > 0) {
                deleteProductImgList(product.getProductId());
                addThumbnailList(product, thumbnailList);
            }
            try {
                int effectedNum = productMapper.updateProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("更新失败！");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS, product);
            } catch (Exception e) {
                throw new ProductOperationException("更新失败！" + toString());
            }
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }


    private void deleteProductImgList(Integer productId) {
        List<ProductImg> productImgList = productImgMapper.queryProductImg(productId);
        for (ProductImg productImg : productImgList) {
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        productImgMapper.deleteProductImgByProductId(productId);
    }





    /*@Override
    public ProductExecution modifyProduct(Product product, InputStream productInputStream, String fileName) throws ProductOperationException {
        return null;
    }*/

    @Override
    @Transactional
    //1，处理缩略图：获取缩略图相对路径并赋值给product;
    //2, 往tb_product写入商品信息，获取productId
    //3, 结合productId批量处理商品详情图片
    //4, 把商品详情图片批量插入tb_product_img中
    public ProductExecution addProduct(Product product, ImageHodler thumbnail, List<ImageHodler> thumbnailList)
            throws ProductOperationException {

        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            //默认为1，商品上架
            product.setEnableStatus(1);
            //缩略图不为空则添加
            if (thumbnail != null) {
                addThumbnail(product, thumbnail);
            }
            try {
                //创建商品信息
                int effectedNum = productMapper.insertProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品失败！");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品失败" + e.getMessage());
            }
            //详情图不为空则添加
            if (thumbnailList != null && thumbnailList.size() > 0) {
                addThumbnailList(product, thumbnailList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS, product);
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }

    private void addThumbnailList(Product product, List<ImageHodler> thumbnailList) {
        String pageAddr = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<ProductImg>();
        for (ImageHodler productImageHodler : thumbnailList) {
            String thumbnailAddr = ImageUtil.generateNormalIm(productImageHodler, pageAddr);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(thumbnailAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);
        }
        if (productImgList.size() > 0) {
            try {
                int effectedNum = productImgMapper.batchInsertProductImg(productImgList);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建图片详情失败！");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建图片详情失败！" + e.toString());
            }
        }

    }

    private void addThumbnail(Product product, ImageHodler thumbnail) {
        String pageAddr = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, pageAddr);
        product.setImgAddr(thumbnailAddr);
    }
}
