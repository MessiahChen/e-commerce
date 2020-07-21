package com.ecommerce.vojo;

import lombok.Data;

import java.util.List;

@Data
public class AddRoleVO {
    private String roleName;
    private String description;
    private List<Long> menus;
}