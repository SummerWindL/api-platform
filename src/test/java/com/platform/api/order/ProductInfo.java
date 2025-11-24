package com.platform.api.order;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductInfo {
    private Long id;
    private String name, description;
    private BigDecimal price;
    private String category;
}
