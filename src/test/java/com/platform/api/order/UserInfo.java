package com.platform.api.order;

import lombok.Data;

@Data
public class UserInfo {
    private Long id;
    private String name, email, phone;
}
