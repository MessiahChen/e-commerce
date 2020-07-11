package com.ecommerce.vojo.wishlist;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductWishlistVO {
    private Integer proId;
    private String image;
    private String title;
    private String minRetailPrice;
    private String retailPrice;
}
