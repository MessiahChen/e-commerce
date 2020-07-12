package com.ecommerce.vojo.browse;

import lombok.Data;

import java.util.List;

@Data
public class ProductDetailVO {
    private Integer proId;
    private String title;
    private String mainCatName;
    private String viceCatName;
    private String minRetailPrice;
    private String retailPrice;
    private List<String> images;
}