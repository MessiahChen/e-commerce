package com.ecommerce.service;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.dto.SearchProductByTitleDTO;
import com.ecommerce.vojo.ProductEntryVO;

import java.util.List;

public interface ProductEntryService {

    CommonPage<ProductEntryVO> searchProductByTitle(SearchProductByTitleDTO searchProductByTitleDTO);
}
