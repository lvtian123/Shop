package com.lv.service.impl;

import com.lv.dao.ShopMapper;
import com.lv.dto.ImageHodler;
import com.lv.dto.ShopExecution;
import com.lv.entity.Shop;
import com.lv.enums.ShopStateEnum;
import com.lv.exception.ShopOperationException;
import com.lv.service.ShopService;
import com.lv.util.ImageUtil;
import com.lv.util.PageUtil;
import com.lv.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;
    //分页返回相应数据
    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex=PageUtil.calculateRowIndex(pageIndex,pageSize);
        List<Shop> shoplist=shopMapper.queryShopList(shopCondition,rowIndex,pageSize);
        int count =shopMapper.queryShopCount(shopCondition);
        ShopExecution se=new ShopExecution();
        if(shoplist!=null){
            se.setShopList(shoplist);
            se.setCount(count);

        }else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;
    }

    @Override
    public Shop queryById(Integer shopId) {
        return shopMapper.queryShopById(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, ImageHodler thumbnail) throws ShopOperationException {

       if(shop==null || shop.getShopId()==null){
           return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
       }else{
           //判断处理的图片，并删除图片地址,编写工具类deleteFileOrPath
            try{
           if(thumbnail.getImage() !=null && thumbnail.getImageName()!=null && !"".equals(thumbnail.getImageName())){
               Shop tempShop=shopMapper.queryShopById(shop.getShopId());
               if(tempShop.getShopImg()!=null){
                   ImageUtil.deleteFileOrPath(tempShop.getShopImg());
               }
               addShopImg(shop,thumbnail);
           }
            //更新店铺信息
           shop.setLastEditTime(new Date());
           int effectedNum=shopMapper.updateShop(shop);
           if(effectedNum<=0){
               return  new ShopExecution(ShopStateEnum.INNER_ERROR);
           }else{
               //操作成功 ，返回查询的值给前台
               shop=shopMapper.queryShopById(shop.getShopId());
               return new ShopExecution(ShopStateEnum.SUCCESS,shop);
           }}catch (Exception e){
                throw   new ShopOperationException("modifyShop err"+e.getMessage());
            }
       }

    }

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, ImageHodler thumbnail) {

        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);
        }
        try {
            //初始化一些参数
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());

            int effectedNum = shopMapper.insertShop(shop);
            if (effectedNum <= 0) {
                throw new ShopOperationException("店铺创建失败");
            } else {
                if (thumbnail != null) {
                    //储存图片
                    try {
                        addShopImg(shop, thumbnail);

                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImge error" + e.getMessage());
                    }
                    effectedNum = shopMapper.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("addShop error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    private void addShopImg(Shop shop, ImageHodler thumbnail) {
        //获取shop图片目录的相对路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        //存储图片，并获取相对值路径
        String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);

        shop.setShopImg(shopImgAddr);

    }


}
