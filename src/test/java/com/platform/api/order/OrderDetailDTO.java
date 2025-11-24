package com.platform.api.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderDetailDTO {
    private UserInfo userInfo;
    private ProductInfo productInfo;
    private ShippingInfo shippingInfo;
    private List<Recommendation> recommendations;

    @Override
    public String toString(){
        return "订单详情: " +
                "\n用户信息: " + userInfo +
                "\n商品信息: " + productInfo +
                "\n物流信息: " + shippingInfo +
                "\n推荐信息: " + recommendations;
    }

}
