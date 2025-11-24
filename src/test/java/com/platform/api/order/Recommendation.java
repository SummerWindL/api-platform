package com.platform.api.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Recommendation {
    private Long id;
    private Long productId;
    private String productName,reason;
    private BigDecimal price;
}
