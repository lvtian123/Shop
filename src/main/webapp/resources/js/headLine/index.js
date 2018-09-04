$(function () {

    var url = '/headline/queryheadline';


    $.getJSON(url, function (data) {
        if (data.success) {
            var headLineList = data.headLineList;
            var tempHtml = '';
            headLineList.map(function (item, index) {
                tempHtml += ''
                    + '<div class="swiper-slide img-warp">'
                    + '<img class="banner-img" src="' + item.lineImg + '" alt="' + item.lineName + '">'
                    + '</div>';
            });
            $('.swiper-wrapper').html(tempHtml);
            $(".swiper-container").swiper({
                autoplay: 2000,
                autoplayDisableOnInteraction: false

            });
            var shopCategoryList = data.shopCategoryList;
            var shopCategoryHtml = '';
            shopCategoryList.map(function (item, index) {
                shopCategoryHtml += ''
                    + '<div class="col-50 shop-classify" data-category='+ item.shopCategoryId +'>'
                    + '<div class="word">'
                    + '<p class="shop-title">' + item.shopCategoryName + '</p>'
                    + '<p class="shop-desc">' + item.shopCategoryDesc + '</p>'
                    + '</div>'
                    + '<div class="shop-classify-img-warp">'
                    + '<img class="shop-img" src="' + item.shopCategoryImg + '">'
                    + '</div>'
                    + '</div>';
            });
            $('.row').html(shopCategoryHtml);
        }

    })


    $('#me').click(function () {
        $.openPanel("#panel-left-demo");
    });

    $('.row').on('click', '.shop-classify', function (e) {
        var shopCategoryId = e.currentTarget.dataset.category;
        var newUrl = '/shopadmin/queryshopcategory?parentId=' + shopCategoryId;
        window.location.href = newUrl;
    });
})