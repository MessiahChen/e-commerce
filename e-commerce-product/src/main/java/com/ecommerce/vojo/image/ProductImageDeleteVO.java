package com.ecommerce.vojo.image;

import com.ecommerce.common.validationGroup.DeleteGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
public class ProductImageDeleteVO {

    @NotNull(message = "无商品ID", groups = {DeleteGroup.class})
    private List<Integer> proIds;
}
