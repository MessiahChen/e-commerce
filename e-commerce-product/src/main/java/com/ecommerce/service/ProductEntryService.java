package com.ecommerce.service;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.vojo.entry.*;

public interface ProductEntryService {

    CommonPage<ProductEntryVO> getAllProduct(GetAllProductVO getAllProductVO);

    CommonPage<ProductEntryVO> searchProductByTitle(SearchProductVO searchProductVO);

    boolean updateProductInfo(ProductUpdateVO productUpdateVO);

    boolean deleteProductInfo(ProductDeleteVO productDeleteVO);

    boolean addProductInfo(ProductAddVO productAddVO);
}
