package com.platform.api.order;


import cn.hutool.core.collection.ListUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.platform.common.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
@Slf4j
public class OrderService {
    /**
     * 并发获取订单完整信息
     */
    public CompletableFuture<OrderDetailDTO> getOrderDetailAsync(Long orderId) {
        // 并行获取各个相关信息
        CompletableFuture<UserInfo> userFuture = getUserInfoAsync(orderId);
        CompletableFuture<ProductInfo> productFuture = getProductInfoAsync(orderId);
        CompletableFuture<ShippingInfo> shippingFuture = getShippingInfoAsync(orderId);
        CompletableFuture<List<Recommendation>> recommendationFuture = getRecommendationsAsync(orderId);

        // 组合所有结果
        return CompletableFuture.allOf(userFuture, productFuture, shippingFuture, recommendationFuture)
                .thenApply(v -> {
                    try {
                        OrderDetailDTO orderDetail = new OrderDetailDTO();
                        orderDetail.setUserInfo(userFuture.get());
                        orderDetail.setProductInfo(productFuture.get());
                        orderDetail.setShippingInfo(shippingFuture.get());
                        orderDetail.setRecommendations(recommendationFuture.get());
                        return orderDetail;
                    } catch (Exception e) {
                        throw new RuntimeException("获取订单详情失败", e);
                    }
                });
    }

    /**
     * 获取用户信息
     */
    private CompletableFuture<UserInfo> getUserInfoAsync(Long orderId) {
        return CompletableFuture.supplyAsync(() -> {
            // 模拟调用用户服务
            // 模拟用户服务调用，实际项目中这里应该是真实的服务调用
            UserInfo mockUser = new UserInfo();
            mockUser.setId(1001L);
            mockUser.setName("张三");
            mockUser.setEmail("zhangsan@example.com");
            mockUser.setPhone("13800138000");
            log.info("获取用户信息: {}", JSONObject.toJSONString(mockUser));
            return mockUser;
        });
    }

    /**
     * 获取商品信息
     */
    private CompletableFuture<ProductInfo> getProductInfoAsync(Long orderId) {
        return CompletableFuture.supplyAsync(() -> {
            // 模拟调用商品服务
            // 模拟调用商品服务
            ProductInfo mockProduct = new ProductInfo();
            mockProduct.setId(2001L);
            mockProduct.setName("iPhone 15 Pro");
            mockProduct.setPrice(BigDecimal.valueOf(9999.00));
            mockProduct.setDescription("最新款苹果手机");
            mockProduct.setCategory("电子产品");
            log.info("获取商品信息: {}" , JSONObject.toJSONString(mockProduct));
            return mockProduct;
        });
    }

    /**
     * 获取物流信息
     */
    private CompletableFuture<ShippingInfo> getShippingInfoAsync(Long orderId) {
        return CompletableFuture.supplyAsync(() -> {
            // 模拟调用物流服务
            // 模拟调用物流服务
            ShippingInfo mockShipping = new ShippingInfo();
            mockShipping.setOrderId(orderId);
            mockShipping.setTrackingNumber("SF123456789CN");
            mockShipping.setStatus("运输中");
            mockShipping.setEstimatedDelivery("2023-12-25");
            mockShipping.setAddress("北京市朝阳区xxx街道xxx号");
            log.info("获取物流信息: {}" , JSONObject.toJSONString(mockShipping));
            return mockShipping;
        });
    }

    /**
     * 获取推荐信息
     */
    private CompletableFuture<List<Recommendation>> getRecommendationsAsync(Long orderId) {
        return CompletableFuture.supplyAsync(() -> {
            // 模拟调用推荐服务

            // 模拟调用推荐服务
            Recommendation rec1 = new Recommendation();
            rec1.setId(3001L);
            rec1.setProductId(2002L);
            rec1.setProductName("AirPods Pro");
            rec1.setPrice(BigDecimal.valueOf(1999.00));
            rec1.setReason("购买此商品的用户还购买了");

            Recommendation rec2 = new Recommendation();
            rec2.setId(3002L);
            rec2.setProductId(2003L);
            rec2.setProductName("MacBook Pro");
            rec2.setPrice(BigDecimal.valueOf(15999.00));
            rec2.setReason("同类热门商品");
            log.info("获取推荐信息: {}" ,JSONArray.toJSONString(ListUtil.of(rec1, rec2)));
            return ListUtil.of(rec1, rec2);
        });
    }
}
