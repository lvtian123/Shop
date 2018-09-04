/*
* 获取商铺分类，区域信息
* 获取表单信息，提交
*
* */

$(function () {
    var shopId=getQueryString('shopId');
    var isEdit=shopId?true:false;
    var initUrl = '/shopadmin/getshopinitinfo';
    var registerShopUrl = '/shopadmin/shopregister';
    var shopInfoUrl= '/shopadmin/getshopbyid?shopId='+shopId;
    var editShopUrl='/shopadmin/modifyShop';

   /* alert(initUrl);*/
    getShopInitInfo();
        if(!isEdit){
        getShopInitInfo();
    }else{
        getShopInfo();
    }
    function getShopInfo(shopId) {
        $.getJSON(shopInfoUrl,function(data) {
            if (data.success) {
               var shop=data.shop;
                $('#shop-name').val(shop.shopName);
                $('#shop-addr').val(shop.shopAddr);
                $('#shop-phone').val(shop.phone);
                $('#shop-desc').val(shop.shopDesc);

                var shopCategory= '<option data-id="' + shop.shopCategory.shopCategoryId + '"selected>'
                        + shop.shopCategory.shopCategoryName + '</option>';
                var tempAreaHtml='';
                data.areaList.map(function(item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">'
                        + item.areaName + '</option>';
                });
                /*填充到控件里*/
                $('#shop-category').attr('disabled','disabled');
                $('#shop-category').html(shopCategory);
                $('#area').html(tempAreaHtml);
                $("#area option[data-id='"+shop.area.areaId+"']").attr("selected","selected");
            }

        });
    }


    function getShopInitInfo() {
        $.getJSON(initUrl,function(data) {
            if (data.success) {
                var tempShopCategoryHtml = '';
                var tempAreaHtml = '';
                data.shopCategoryList.map(function (item, index) {
                    tempShopCategoryHtml += '<option data-id="' + item.shopCategoryId + '">'
                        + item.shopCategoryName + '</option>';
                })
                data.areaList.map(function(item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">'
                        + item.areaName + '</option>';
                });
                /*填充到控件里*/
                $('#shop-category').html(tempShopCategoryHtml);
                $('#area').html(tempAreaHtml);
            }

        })
        /*提交按钮*/
        $('#submit').click(function (){
            var shop = {};
            if(isEdit){
                shop.shopId=shopId;
            }
            shop.shopName = $('#shop-name').val();
            shop.shopAddr = $('#shop-addr').val();
            shop.phone = $('#shop-phone').val();
            shop.shopDesc = $('#shop-desc').val();
            shop.shopCategory = {
                shopCategoryId: $('#shop-category').find('option').not(function () {
                    return !this.selected;
                }).data('id')
            };
            shop.area = {
                areaId: $('#area').find('option').not(function () {
                    return !this.selected;
                }).data('id')
            };
            /*图片流*/
            var shopImg = $("#shop-img")[0].files[0];
            var formData = new FormData();
            formData.append('shopImg', shopImg);
            formData.append('shopStr', JSON.stringify(shop));
            var verifyCodeActual=$('#j_captcha').val();
            if(!verifyCodeActual){
                $.toast('请输入验证码！');
                return;
            }
            formData.append('verifyCodeActual',verifyCodeActual);
            $.ajax({

                url: (isEdit?editShopUrl:registerShopUrl),
                type: 'POST',
                data: formData,

                contentType: false,
                processData: false,
                cache: false,

                /*contentType: undefined,*/
                success: function (data) {
                    if (data.success) {
                        $.toast('提交成功！');
                    }else {
                        $.toast('提交失败！',data.errMsg);
                    }
                    $('#captcha_img').click();
                }

            });
        })
    }

})














