package com.ecommerce.vojo.image;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class ProductCategoryVO {
    private Integer catId;
    private String catName;

    private List<ViceCategory> viceCats;
}
