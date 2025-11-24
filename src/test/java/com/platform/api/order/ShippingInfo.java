package com.platform.api.order;

import lombok.Data;

@Data
public class ShippingInfo {
    private Long orderId;
    private String name, phone, address, status;
    private Double cost;
    private String trackingNumber;
    private String estimatedDelivery;
}
