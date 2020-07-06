package com.ecommerce.service;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.vojo.entry.GetAllProductVO;
import com.ecommerce.vojo.entry.ProductEntryVO;
import com.ecommerce.vojo.image.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductImageService {

    CommonPage<ProductImageVO> getAllProductImage(GetAllProductImageVO getAllProductImageVO);

    CommonPage<ProductImageVO> searchProductImageByTitle(SearchProductImageVO searchProductImageVO);

    boolean updateProductImage(ProductCategoryUpdateVO productCategoryUpdateVO);

    boolean deleteProductImage(ProductImageDeleteVO productImageDeleteVO);

    List<ProductCategoryVO> getAllCategory();

    boolean addProductCategory(ProductCategoryAddVO productCategoryAddVO);

    List<String> uploadImages(List<MultipartFile> files);

}
