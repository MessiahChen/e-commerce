package com.ecommerce.service.impl;

import com.ecommerce.dao.*;
import com.ecommerce.dto.product.browse.ProductBrowseDTO;
import com.ecommerce.dto.product.browse.ProductDetailDTO;
import com.ecommerce.pojo.*;
import com.ecommerce.service.ProductBrowseService;
import com.ecommerce.vojo.browse.OperateWishlistVO;
import com.ecommerce.vojo.browse.ProductBrowseWithCatVO;
import com.ecommerce.vojo.browse.ProductDetailVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private WitWishlistMapper witWishlistMapper;

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
    public ProductDetailVO getProductInfoDetailById(Integer dsrId, Integer proId) {
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

        WitWishlistExample example = new WitWishlistExample();
        WitWishlistExample.Criteria criteria_wit = example.createCriteria();
        criteria_wit.andDsrIdEqualTo(dsrId);
        criteria_wit.andProIdEqualTo(proId);
        List<WitWishlist> witWishlists = witWishlistMapper.selectByExample(example);

        if (witWishlists == null || witWishlists.isEmpty() || witWishlists.get(0).getStsCd().equals("0")) {
            result.setIfInWishlist(false);
        } else if (witWishlists.get(0).getStsCd().equals("1")) {
            result.setIfInWishlist(true);
        }

        result.setImages(images);
        return result;
    }

    @Override
    public boolean addToWishlist(OperateWishlistVO vo) {
        WitWishlistExample example = new WitWishlistExample();
        WitWishlistExample.Criteria criteria = example.createCriteria();
        criteria.andDsrIdEqualTo(vo.getDsrId());
        criteria.andProIdEqualTo(vo.getProId());

        WitWishlist witWishlist = new WitWishlist();
        witWishlist.setStsCd("1");

        long ifExit = witWishlistMapper.countByExample(example);
        if (ifExit == 1) {
            witWishlist.setLastUpdateBy(vo.getUserId());
            witWishlist.setLastUpdateDate(new Date());
            return witWishlistMapper.updateByExampleSelective(witWishlist, example) == 1;
        } else {
            witWishlist.setDsrId(vo.getDsrId());
            witWishlist.setProId(vo.getProId());
            witWishlist.setCreatedBy(vo.getUserId());
            witWishlist.setCreationDate(new Date());
            return witWishlistMapper.insertSelective(witWishlist) == 1;
        }
    }

    @Override
    public boolean deleteFromWishlist(OperateWishlistVO vo) {
        WitWishlistExample example = new WitWishlistExample();
        WitWishlistExample.Criteria criteria = example.createCriteria();
        criteria.andDsrIdEqualTo(vo.getDsrId());
        criteria.andProIdEqualTo(vo.getProId());

        WitWishlist witWishlist = new WitWishlist();
        witWishlist.setLastUpdateBy(vo.getUserId());
        witWishlist.setLastUpdateDate(new Date());
        witWishlist.setStsCd("0");

        return witWishlistMapper.updateByExampleSelective(witWishlist, example) == 1;
    }
}
