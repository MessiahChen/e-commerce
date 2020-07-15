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

    public Integer getProId() {
        return proId;
    }

    @Override
    public String toString() {
        return "ProductDetailVO{" +
                "proId=" + proId +
                ", title='" + title + '\'' +
                ", mainCatName='" + mainCatName + '\'' +
                ", viceCatName='" + viceCatName + '\'' +
                ", minRetailPrice='" + minRetailPrice + '\'' +
                ", retailPrice='" + retailPrice + '\'' +
                ", images=" + images +
                '}';
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainCatName() {
        return mainCatName;
    }

    public void setMainCatName(String mainCatName) {
        this.mainCatName = mainCatName;
    }

    public String getViceCatName() {
        return viceCatName;
    }

    public void setViceCatName(String viceCatName) {
        this.viceCatName = viceCatName;
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    private List<String> images;
}
