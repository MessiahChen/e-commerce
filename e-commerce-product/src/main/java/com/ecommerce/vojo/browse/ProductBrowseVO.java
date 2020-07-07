package com.ecommerce.vojo.browse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductBrowseVO {
    private Integer proId;
    private String image;
    private String title;
    private String minRetailPrice;
    private String retailPrice;
}
