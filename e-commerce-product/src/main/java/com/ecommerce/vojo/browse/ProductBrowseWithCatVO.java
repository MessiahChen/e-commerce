package com.ecommerce.vojo.browse;

import com.ecommerce.dto.product.browse.ProductBrowseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ProductBrowseWithCatVO {
    private String catName;

    private List<ProductBrowseDTO> products;

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

    @Override
    public String toString() {
        return "ProductBrowseWithCatVO{" +
                "catName='" + catName + '\'' +
                ", products=" + products +
                '}';
    }
}
