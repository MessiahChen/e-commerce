package com.ecommerce.service;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.vojo.entry.ProductAddVO;
import com.ecommerce.vojo.entry.ProductDeleteVO;
import com.ecommerce.vojo.entry.ProductEntryVO;
import com.ecommerce.vojo.entry.ProductUpdateVO;

public interface ProductEntryService {

    CommonPage<ProductEntryVO> searchProductByTitle(String title, Integer pageNum, Integer pageSize);

    boolean updateProductInfo(ProductUpdateVO productUpdateVO);

    boolean deleteProductInfo(ProductDeleteVO productDeleteVO);

    boolean addProductInfo(ProductAddVO productAddVO);
}
