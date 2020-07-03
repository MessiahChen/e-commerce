package com.ecommerce.service.impl;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.dao.ImgImageMapper;
import com.ecommerce.dao.PckPackageInfoMapper;
import com.ecommerce.dao.PrcProductCategoryMapper;
import com.ecommerce.dao.ProProductMapper;
import com.ecommerce.pojo.*;
import com.ecommerce.service.ProductEntryService;
import com.ecommerce.service.ProductImageService;
import com.ecommerce.util.OSSClientUtil;
import com.ecommerce.vojo.entry.ProductAddVO;
import com.ecommerce.vojo.entry.ProductDeleteVO;
import com.ecommerce.vojo.entry.ProductEntryVO;
import com.ecommerce.vojo.entry.ProductUpdateVO;
import com.ecommerce.vojo.image.ProductImageAddVO;
import com.ecommerce.vojo.image.ProductImageDeleteVO;
import com.ecommerce.vojo.image.ProductImageUpdateVO;
import com.ecommerce.vojo.image.ProductImageVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProProductMapper proProductMapper;

    @Autowired
    private ImgImageMapper imgImageMapper;

    @Autowired
    private PrcProductCategoryMapper prcProductCategoryMapper;

    @Override
    public CommonPage<ProductImageVO> searchProductImageByTitle(String title, Integer pageNum, Integer pageSize) {
        Page<ProProduct> productPage = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> {
            ProProductExample proProductExample = new ProProductExample();
            ProProductExample.Criteria criteria = proProductExample.createCriteria();
            criteria.andTitleLike("%" + title + "%");
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

            PrcProductCategoryExample prcProductCategoryExample = new PrcProductCategoryExample();
            PrcProductCategoryExample.Criteria criteria_prc = prcProductCategoryExample.createCriteria();
            criteria_prc.andProIdEqualTo(product.getProId());
            List<PrcProductCategory> prcProductCategories = prcProductCategoryMapper.selectByExample(prcProductCategoryExample);
            productImageVO.setCategoryName(prcProductCategories.get(0).getCategoryName());

            ImgImageExample imgImageExample = new ImgImageExample();
            ImgImageExample.Criteria criteria_img = imgImageExample.createCriteria();
            criteria_img.andEntityIdEqualTo(product.getProId());
            List<ImgImage> imgImages = imgImageMapper.selectByExample(imgImageExample);
            productImageVO.setImageUri(imgImages.get(0).getUri());

            result.add(productImageVO);
        }
        return CommonPage.restPage(result, productPage);
    }

    @Override
    public boolean updateProductImage(ProductImageUpdateVO vo) {

        return true;
    }

    @Override
    public boolean deleteProductImage(ProductImageDeleteVO vo) {
        // 删除商品图片、分类
        imgImageMapper.deleteProductImageByList(vo.getProIds());
        prcProductCategoryMapper.deleteProductCategoryByList(vo.getProIds());
        return true;
    }

    @Override
    public boolean addProductImage(ProductImageAddVO vo) {

        return true;
    }

    @Override
    public List<String> uploadImages(MultipartFile files) {
        List<String> imgUrls = new ArrayList<>();
        if (files.isEmpty()) System.out.println(1);
        try {
            OSSClientUtil ossClient = new OSSClientUtil();
            if (files == null || files.getSize() <= 0) {
                throw new Exception("某一图片为空");
            }
            String name = ossClient.uploadHomeImageOSS(files);
            String imgUrl = ossClient.getHomeImageUrl(name);
            System.out.println(name + "  " + imgUrl);
            imgUrls.add(imgUrl);
//            for (int i = 0; i < files.size(); i++) {
//                if (files.get(i) == null || files.get(i).getSize() <= 0) {
//                    throw new Exception("某一图片为空");
//                }
//                String name = ossClient.uploadHomeImageOSS(files.get(i));
//                String imgUrl = ossClient.getHomeImageUrl(name);
//                System.out.println(name + "  " + imgUrl);
//                imgUrls.add(imgUrl);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgUrls;
    }
}
