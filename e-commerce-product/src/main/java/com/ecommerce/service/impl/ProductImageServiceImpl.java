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
    public boolean updateProductImage(ProductCategoryUpdateVO vo) {
        PrcProductCategory prcProductCategory = new PrcProductCategory();
        prcProductCategory.setProId(vo.getProId());
        prcProductCategory.setCategoryId(vo.getMainCatId());
        prcProductCategory.setCategoryName(vo.getMainCatName());
        prcProductCategory.setCategoryPath(vo.getMainCatName() + vo.getViceCatName());
        prcProductCategory.setCreatedBy(vo.getUserId());
        prcProductCategory.setCreationDate(new Date());
        prcProductCategory.setLastUpdateBy(vo.getUserId());
        prcProductCategory.setLastUpdateDate(new Date());

        prcProductCategoryMapper.insertSelective(prcProductCategory);

        prcProductCategory.setCategoryId(vo.getViceCatId());
        prcProductCategory.setCategoryName(vo.getViceCatName());

        prcProductCategoryMapper.insertSelective(prcProductCategory);

        ProProduct proProduct = new ProProduct();
        proProduct.setProId(vo.getProId());
        proProduct.setPrcId(vo.getMainCatId());
        proProductMapper.updateByPrimaryKeySelective(proProduct);
        return true;
    }

    @Override
    public boolean deleteProductImage(ProductImageDeleteVO vo) {
        // 删除商品图片、分类
        List<String> proIds = new ArrayList<>();
        for (int pro_id : vo.getProIds()) {
            proIds.add(String.valueOf(pro_id));
        }

        imgImageMapper.deleteProductImageByList(proIds);
        prcProductCategoryMapper.deleteProductCategoryByList(vo.getProIds());
        return true;
    }

    @Override
    public List<ProductCategoryVO> getAllCategory() {
        List<ProductCategoryVO> result = new ArrayList<>();
        CatCategoryExample catCategoryExample = new CatCategoryExample();
        CatCategoryExample.Criteria criteria_cat = catCategoryExample.createCriteria();
        criteria_cat.andCatFatherIdIsNull();

        List<CatCategory> mainCatCategories = catCategoryMapper.selectByExample(catCategoryExample);

        CatCategoryExample viceCatCategoryExample = new CatCategoryExample();
        CatCategoryExample.Criteria criteria_vice_cat_ = viceCatCategoryExample.createCriteria();

        for (CatCategory cat : mainCatCategories) {
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
            productCategoryVO.setCatId(cat.getCatId());
            productCategoryVO.setCatName(cat.getCatName());
            List<ViceCategory> viceCats = productCategoryVO.getViceCats();

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

    @Override
    public boolean addProductCategory(ProductCategoryAddVO vo) {
        PrcProductCategory prcProductCategory = new PrcProductCategory();
        prcProductCategory.setProId(vo.getProId());
        prcProductCategory.setCategoryId(vo.getMainCatId());
        prcProductCategory.setCategoryName(vo.getMainCatName());
        prcProductCategory.setCategoryPath(vo.getMainCatName() + vo.getViceCatName());
        prcProductCategory.setCreatedBy(vo.getUserId());
        prcProductCategory.setCreationDate(new Date());
        prcProductCategory.setLastUpdateBy(vo.getUserId());
        prcProductCategory.setLastUpdateDate(new Date());

        prcProductCategoryMapper.insertSelective(prcProductCategory);

        prcProductCategory.setCategoryId(vo.getViceCatId());
        prcProductCategory.setCategoryName(vo.getViceCatName());

        prcProductCategoryMapper.insertSelective(prcProductCategory);

        ProProduct proProduct = new ProProduct();
        proProduct.setProId(vo.getProId());
        proProduct.setPrcId(vo.getMainCatId());
        proProductMapper.updateByPrimaryKeySelective(proProduct);
        return true;
    }


    @Override
    public List<String> uploadImages(List<MultipartFile> files) {
        List<String> imgUrls = new ArrayList<>();
        if (files.isEmpty()) System.out.println(1);
        try {
            OSSClientUtil ossClient = new OSSClientUtil();
//            if (files == null || files.getSize() <= 0) {
//                throw new Exception("某一图片为空");
//            }
//            String name = ossClient.uploadHomeImageOSS(files);
//            String imgUrl = ossClient.getHomeImageUrl(name);
//            System.out.println(name + "  " + imgUrl);
//            imgUrls.add(imgUrl);

            for (int i = 0; i < files.size(); i++) {
                if (files.get(i) == null || files.get(i).getSize() <= 0) {
                    throw new Exception("某一图片为空");
                }
                String name = ossClient.uploadHomeImageOSS(files.get(i));
                String imgUrl = ossClient.getHomeImageUrl(name);
                System.out.println(name + "  " + imgUrl);
                imgUrls.add(imgUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgUrls;
    }
}
