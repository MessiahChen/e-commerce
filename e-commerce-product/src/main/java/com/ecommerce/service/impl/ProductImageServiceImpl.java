package com.ecommerce.service.impl;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.dao.CatCategoryMapper;
import com.ecommerce.dao.ImgImageMapper;
import com.ecommerce.dao.PrcProductCategoryMapper;
import com.ecommerce.dao.ProProductMapper;
import com.ecommerce.pojo.*;
import com.ecommerce.service.ProductImageService;
import com.ecommerce.util.OSSClientUtil;
import com.ecommerce.vojo.image.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProProductMapper proProductMapper;

    @Autowired
    private ImgImageMapper imgImageMapper;

    @Autowired
    private PrcProductCategoryMapper prcProductCategoryMapper;

    @Autowired
    private CatCategoryMapper catCategoryMapper;

    @Override
    public CommonPage<ProductImageVO> getAllProductImage(GetAllProductImageVO vo) {
        Page<ProProduct> productPage = PageHelper.startPage(vo.getPageNum(), vo.getPageSize()).doSelectPage(() -> {
            ProProductExample proProductExample = new ProProductExample();
            ProProductExample.Criteria criteria = proProductExample.createCriteria();
            criteria.andManIdEqualTo(vo.getManId());
            proProductMapper.selectByExample(proProductExample);
        });

        // 需要从分类表中找到分类；需要从图片表中找到主URI
        List<ProductImageVO> result = new ArrayList<>();
        for (ProProduct product : productPage.getResult()) {
            ProductImageVO productImageVO = new ProductImageVO();
            productImageVO.setProId(product.getProId());
            productImageVO.setTitle(product.getTitle());
            String statusChar = product.getStsCd();
            if (statusChar.equals("a")) {
                statusChar = "待入仓";
            } else if (statusChar.equals("b")) {
                statusChar = "入仓中";
            } else if (statusChar.equals("c")) {
                statusChar = "待上架";
            } else {
                statusChar = "上架中";
            }
            productImageVO.setStatus(statusChar);

            PrcProductCategory prcProductCategory = prcProductCategoryMapper.selectByPrimaryKey(product.getPrcId());
            if (prcProductCategory != null)
                productImageVO.setCategoryName(prcProductCategory.getCategoryPath());

            ImgImageExample imgImageExample = new ImgImageExample();
            ImgImageExample.Criteria criteria_img = imgImageExample.createCriteria();
            criteria_img.andEntityIdEqualTo(String.valueOf(product.getProId()));
            List<ImgImage> imgImages = imgImageMapper.selectByExample(imgImageExample);
            if (!imgImages.isEmpty())
                productImageVO.setImageUri(imgImages.get(0).getUri());

            result.add(productImageVO);
        }
        return CommonPage.restPage(result, productPage);
    }

    @Override
    public CommonPage<ProductImageVO> searchProductImageByTitle(SearchProductImageVO vo) {
        Page<ProProduct> productPage = PageHelper.startPage(vo.getPageNum(), vo.getPageSize()).doSelectPage(() -> {
            ProProductExample proProductExample = new ProProductExample();
            ProProductExample.Criteria criteria = proProductExample.createCriteria();
            criteria.andTitleLike("%" + vo.getTitle() + "%");
            proProductMapper.selectByExample(proProductExample);
        });

        // 需要从分类表中找到分类；需要从图片表中找到主URI
        List<ProductImageVO> result = new ArrayList<>();
        for (ProProduct product : productPage.getResult()) {
            ProductImageVO productImageVO = new ProductImageVO();
            productImageVO.setProId(product.getProId());
            productImageVO.setTitle(product.getTitle());
            String statusChar = product.getStsCd();
            if (statusChar.equals("a")) {
                statusChar = "待入仓";
            } else if (statusChar.equals("b")) {
                statusChar = "入仓中";
            } else if (statusChar.equals("c")) {
                statusChar = "待上架";
            } else {
                statusChar = "上架中";
            }
            productImageVO.setStatus(statusChar);

            PrcProductCategory prcProductCategory = prcProductCategoryMapper.selectByPrimaryKey(product.getPrcId());
            if (prcProductCategory != null)
                productImageVO.setCategoryName(prcProductCategory.getCategoryName());

            ImgImageExample imgImageExample = new ImgImageExample();
            ImgImageExample.Criteria criteria_img = imgImageExample.createCriteria();
            criteria_img.andEntityIdEqualTo(String.valueOf(product.getProId()));
            List<ImgImage> imgImages = imgImageMapper.selectByExample(imgImageExample);
            if (!imgImages.isEmpty())
                productImageVO.setImageUri(imgImages.get(0).getUri());

            result.add(productImageVO);
        }
        return CommonPage.restPage(result, productPage);
    }

    @Override
    public boolean addProductCategory(ProductCategoryAddVO vo) {
        int mainCatId = vo.getCategory().get(0);
        int viceCatId = vo.getCategory().get(1);

        CatCategory mainCatCategory = catCategoryMapper.selectByPrimaryKey(mainCatId);
        CatCategory viceCatCategory = catCategoryMapper.selectByPrimaryKey(viceCatId);

        PrcProductCategory prcProductCategory = new PrcProductCategory();
        prcProductCategory.setProId(vo.getProId());
        prcProductCategory.setCategoryId(mainCatId);
        prcProductCategory.setCategoryName(mainCatCategory.getCatName());
        prcProductCategory.setCategoryPath(mainCatCategory.getCatName() + "/" + viceCatCategory.getCatName());
        prcProductCategory.setCreatedBy(vo.getUserId());
        prcProductCategory.setCreationDate(new Date());
        prcProductCategory.setLastUpdateBy(vo.getUserId());
        prcProductCategory.setLastUpdateDate(new Date());

        prcProductCategoryMapper.insertSelective(prcProductCategory);

        ProProduct proProduct = new ProProduct();
        proProduct.setProId(vo.getProId());
        proProduct.setPrcId(prcProductCategory.getPrcId());

        prcProductCategory.setCategoryId(viceCatId);
        prcProductCategory.setCategoryName(viceCatCategory.getCatName());
        prcProductCategoryMapper.insertSelective(prcProductCategory);

        proProductMapper.updateByPrimaryKeySelective(proProduct);
        return true;
    }

    @Override
    public boolean changeProStatus(ProductStatusVO vo) {
        String status = vo.getStatus();
        String statusChar = "";
        if (status.equals("待入仓")) {
            statusChar = "b";
        } else if (status.equals("待上架")) {
            statusChar = "d";
        } else if (status.equals("上架中")) {
            statusChar = "c";
        } else {
            statusChar = "b";
        }
        ProProduct proProduct = new ProProduct();
        proProduct.setProId(vo.getProId());
        proProduct.setStsCd(statusChar);

        return proProductMapper.updateByPrimaryKeySelective(proProduct) == 1;

    }

    @Override
    public ProductCategoryAddVO getProductCatWhenUpdate(Integer proId) {
        ProductCategoryAddVO productCategoryAddVO = new ProductCategoryAddVO();
        productCategoryAddVO.setProId(proId);


        return null;
    }

    @Override
    public boolean updateProductImage(ProductCategoryUpdateVO vo) {
        PrcProductCategoryExample prcProductCategoryExample = new PrcProductCategoryExample();
        PrcProductCategoryExample.Criteria criteria = prcProductCategoryExample.createCriteria();
        criteria.andProIdEqualTo(vo.getProId());

        prcProductCategoryMapper.deleteByExample(prcProductCategoryExample);

        int mainCatId = vo.getCategory().get(0);
        int viceCatId = vo.getCategory().get(1);

        CatCategory mainCatCategory = catCategoryMapper.selectByPrimaryKey(mainCatId);
        CatCategory viceCatCategory = catCategoryMapper.selectByPrimaryKey(viceCatId);

        PrcProductCategory prcProductCategory = new PrcProductCategory();
        prcProductCategory.setProId(vo.getProId());
        prcProductCategory.setCategoryId(mainCatId);
        prcProductCategory.setCategoryName(mainCatCategory.getCatName());
        prcProductCategory.setCategoryPath(mainCatCategory.getCatName() + "/" + viceCatCategory.getCatName());
        prcProductCategory.setCreatedBy(vo.getUserId());
        prcProductCategory.setCreationDate(new Date());
        prcProductCategory.setLastUpdateBy(vo.getUserId());
        prcProductCategory.setLastUpdateDate(new Date());

        prcProductCategoryMapper.insertSelective(prcProductCategory);

        ProProduct proProduct = new ProProduct();
        proProduct.setProId(vo.getProId());
        proProduct.setPrcId(prcProductCategory.getPrcId());

        prcProductCategory.setCategoryId(viceCatId);
        prcProductCategory.setCategoryName(viceCatCategory.getCatName());
        prcProductCategoryMapper.insertSelective(prcProductCategory);

        proProductMapper.updateByPrimaryKeySelective(proProduct);
        return true;
    }

    @Override
    public boolean deleteProductImage(List<Integer> proIds) {
        // 删除商品图片、分类
        List<String> stringProIds = new ArrayList<>();
        for (int pro_id : proIds) {
            stringProIds.add(String.valueOf(pro_id));
        }

        imgImageMapper.deleteProductImageByList(stringProIds);
        prcProductCategoryMapper.deleteProductCategoryByList(proIds);
        return true;
    }

    @Override
    public List<ProductCategoryVO> getAllCategory() {
        List<ProductCategoryVO> result = new ArrayList<>();
        CatCategoryExample catCategoryExample = new CatCategoryExample();
        CatCategoryExample.Criteria criteria_cat = catCategoryExample.createCriteria();
        criteria_cat.andCatFatherIdIsNull();

        List<CatCategory> mainCatCategories = catCategoryMapper.selectByExample(catCategoryExample);

        for (CatCategory cat : mainCatCategories) {
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
            productCategoryVO.setCatId(cat.getCatId());
            productCategoryVO.setCatName(cat.getCatName());
            List<ViceCategory> viceCats = productCategoryVO.getViceCats();

            CatCategoryExample viceCatCategoryExample = new CatCategoryExample();
            CatCategoryExample.Criteria criteria_vice_cat_ = viceCatCategoryExample.createCriteria();
            criteria_vice_cat_.andCatFatherIdEqualTo(cat.getCatId());

            List<CatCategory> viceCatCategories = catCategoryMapper.selectByExample(viceCatCategoryExample);

            if (!viceCatCategories.isEmpty()) {
                for (CatCategory viceCat : viceCatCategories) {
                    ViceCategory viceCategory = new ViceCategory();
                    viceCategory.setCatId(viceCat.getCatId());
                    viceCategory.setCatName(viceCat.getCatName());
                    viceCats.add(viceCategory);
                }
            }
            result.add(productCategoryVO);
        }
        return result;
    }

}
