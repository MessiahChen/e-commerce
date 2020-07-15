package com.ecommerce.dto.product.browse;

import lombok.Data;

@Data
public class ProductBrowseDTO {
    private Integer proId;
    private String image;
    private String title;

    public ProductBrowseDTO(Integer proId, String image, String title, String minRetailPrice, String retailPrice) {
        this.proId = proId;
        this.image = image;
        this.title = title;
        this.minRetailPrice = minRetailPrice;
        this.retailPrice = retailPrice;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMinRetailPrice() {
        return minRetailPrice;
    }

    public void setMinRetailPrice(String minRetailPrice) {
        this.minRetailPrice = minRetailPrice;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    private String minRetailPrice;
    private String retailPrice;
}
