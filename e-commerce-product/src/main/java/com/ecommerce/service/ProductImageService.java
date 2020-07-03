package com.ecommerce.service;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.vojo.entry.ProductAddVO;
import com.ecommerce.vojo.entry.ProductDeleteVO;
import com.ecommerce.vojo.entry.ProductEntryVO;
import com.ecommerce.vojo.entry.ProductUpdateVO;
import com.ecommerce.vojo.image.ProductImageAddVO;
import com.ecommerce.vojo.image.ProductImageDeleteVO;
import com.ecommerce.vojo.image.ProductImageUpdateVO;
import com.ecommerce.vojo.image.ProductImageVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductImageService {

    CommonPage<ProductImageVO> searchProductImageByTitle(String title, Integer pageNum, Integer pageSize);

    boolean updateProductImage(ProductImageUpdateVO productImageUpdateVO);

    boolean deleteProductImage(ProductImageDeleteVO productImageDeleteVO);

    boolean addProductImage(ProductImageAddVO productImageAddVO);

    List<String> uploadImages(MultipartFile files);
}
