package com.ecommerce.vojo.wishlist;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProductWishlistWithCatVO {
    private String catName;

    private List<ProductCatsVO> products;
}
