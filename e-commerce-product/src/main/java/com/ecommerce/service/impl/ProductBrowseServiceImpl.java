package com.ecommerce.service.impl;

import com.ecommerce.dao.CatCategoryMapper;
import com.ecommerce.dao.ImgImageMapper;
import com.ecommerce.dao.PrcProductCategoryMapper;
import com.ecommerce.dao.ProProductMapper;
import com.ecommerce.dto.product.browse.ProductBrowseDTO;
import com.ecommerce.pojo.CatCategory;
import com.ecommerce.pojo.CatCategoryExample;
import com.ecommerce.pojo.ProProduct;
import com.ecommerce.pojo.ProProductExample;
import com.ecommerce.service.ProductBrowseService;
import com.ecommerce.vojo.browse.ProductBrowseWithCatVO;
import com.ecommerce.vojo.image.ProductCategoryVO;
import com.ecommerce.vojo.image.ViceCategory;
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
}
