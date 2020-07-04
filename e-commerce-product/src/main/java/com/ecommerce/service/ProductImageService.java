package com.ecommerce.service;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.vojo.image.ProductCategoryAddVO;
import com.ecommerce.vojo.image.ProductImageDeleteVO;
import com.ecommerce.vojo.image.ProductCategoryUpdateVO;
import com.ecommerce.vojo.image.ProductImageVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductImageService {

    CommonPage<ProductImageVO> searchProductImageByTitle(String title, Integer pageNum, Integer pageSize);

    boolean updateProductImage(ProductCategoryUpdateVO productCategoryUpdateVO);

    boolean deleteProductImage(ProductImageDeleteVO productImageDeleteVO);

    boolean addProductCategory(ProductCategoryAddVO productCategoryAddVO);

    List<String> uploadImages(List<MultipartFile> files);

}
