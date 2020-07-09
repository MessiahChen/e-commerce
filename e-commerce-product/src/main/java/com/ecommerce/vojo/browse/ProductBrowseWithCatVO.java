package com.ecommerce.vojo.browse;

import com.ecommerce.dto.product.browse.ProductBrowseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProductBrowseWithCatVO {
    private String catName;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public List<ProductBrowseDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBrowseDTO> products) {
        this.products = products;
    }

    private List<ProductBrowseDTO> products;
}
