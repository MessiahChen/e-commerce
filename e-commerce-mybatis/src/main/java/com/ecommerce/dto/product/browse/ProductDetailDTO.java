package com.ecommerce.dto.product.browse;

import lombok.Data;

@Data
public class ProductDetailDTO {
    private Integer proId;
    private String title;
    private String categoryPath;
    private String minRetailPrice;
    private String retailPrice;
}
