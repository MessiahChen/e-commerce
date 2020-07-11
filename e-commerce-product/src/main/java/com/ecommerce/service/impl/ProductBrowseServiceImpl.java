package com.ecommerce.service.impl;

import com.ecommerce.dao.CatCategoryMapper;
import com.ecommerce.dao.ImgImageMapper;
import com.ecommerce.dao.PrcProductCategoryMapper;
import com.ecommerce.dao.ProProductMapper;
import com.ecommerce.dto.product.browse.ProductBrowseDTO;
import com.ecommerce.dto.product.browse.ProductDetailDTO;
import com.ecommerce.pojo.*;
import com.ecommerce.service.ProductBrowseService;
import com.ecommerce.vojo.browse.ProductBrowseWithCatVO;
import com.ecommerce.vojo.browse.ProductDetailVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductBrowseServiceImpl implements ProductBrowseService {

    @Autowired
    private ProProductMapper proProductMapper;

    @Autowired
    private PrcProductCategoryMapper prcProductCategoryMapper;

    @Autowired
    private ImgImageMapper imgImageMapper;

    @Autowired
    private CatCategoryMapper catCategoryMapper;

    @Override
    public List<ProductBrowseWithCatVO> getAllProductWithStatD() {
        List<ProductBrowseWithCatVO> result = new ArrayList<>();
        // 将所有主分类取出来
        // 去prc中找到不同主分类对应的pro_id
        // 根据pro_id找到pro
        CatCategoryExample catCategoryExample = new CatCategoryExample();
        CatCategoryExample.Criteria criteria_cat = catCategoryExample.createCriteria();
        criteria_cat.andCatFatherIdIsNull();

        List<CatCategory> mainCatCategories = catCategoryMapper.selectByExample(catCategoryExample);

        for (CatCategory cat : mainCatCategories) {
            ProductBrowseWithCatVO productBrowseWithCatVO = new ProductBrowseWithCatVO();
            productBrowseWithCatVO.setCatName(cat.getCatName());
            List<ProductBrowseDTO> productBrowseDTOS = proProductMapper.selectProByPrcCat(cat.getCatId());
            productBrowseWithCatVO.setProducts(productBrowseDTOS);

            result.add(productBrowseWithCatVO);
        }
        return result;
    }

    @Override
    public ProductDetailVO getProductInfoDetailById(Integer proId) {
        ProductDetailDTO productDetailDTO = proProductMapper.selectProDetailById(proId);
        ProductDetailVO result = new ProductDetailVO();
        result.setProId(proId);
        result.setTitle(productDetailDTO.getTitle());
        String[] cats = productDetailDTO.getCategoryPath().split("/");
        result.setMainCatName(cats[0]);
        result.setViceCatName(cats[1]);
        result.setMinRetailPrice(productDetailDTO.getMinRetailPrice());
        result.setRetailPrice(productDetailDTO.getRetailPrice());

        ImgImageExample imgImageExample = new ImgImageExample();
        ImgImageExample.Criteria criteria = imgImageExample.createCriteria();
        criteria.andEntityIdEqualTo(String.valueOf(proId));

        List<ImgImage> imgImages = imgImageMapper.selectByExample(imgImageExample);
        ArrayList<String> images = new ArrayList<>();
        imgImages.forEach(imgImage -> {
            images.add(imgImage.getUri());
        });

        result.setImages(images);
        return result;
    }
}
