package com.ecommerce.service;

import com.ecommerce.vojo.browse.ProductBrowseWithCatVO;

import java.util.List;

public interface ProductBrowseService {

    // 获得数据接口
    List<ProductBrowseWithCatVO> getAllProductWithStatD();

}
