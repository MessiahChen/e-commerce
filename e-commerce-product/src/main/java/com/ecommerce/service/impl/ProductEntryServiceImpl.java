package com.ecommerce.service.impl;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.dao.PckPackageInfoMapper;
import com.ecommerce.dao.ProProductMapper;
import com.ecommerce.pojo.PckPackageInfo;
import com.ecommerce.pojo.PckPackageInfoExample;
import com.ecommerce.pojo.ProProduct;
import com.ecommerce.pojo.ProProductExample;
import com.ecommerce.service.ProductEntryService;
import com.ecommerce.vojo.entry.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductEntryServiceImpl implements ProductEntryService {

    @Autowired
    private ProProductMapper proProductMapper;

    @Autowired
    private PckPackageInfoMapper pckPackageInfoMapper;

    @Override
    public CommonPage<ProductEntryVO> getAllProduct(GetAllProductVO vo) {
        Page<ProProduct> productPage = PageHelper.startPage(vo.getPageNum(), vo.getPageSize()).doSelectPage(() -> {
            ProProductExample proProductExample = new ProProductExample();
            ProProductExample.Criteria criteria = proProductExample.createCriteria();
            criteria.andManIdEqualTo(vo.getManId());
            proProductMapper.selectByExample(proProductExample);
        });

        List<ProductEntryVO> result = new ArrayList<>();
        for (ProProduct product : productPage.getResult()) {
            ProductEntryVO productEntryVO = new ProductEntryVO();
            productEntryVO.setProId(product.getProId());
            productEntryVO.setRetailPrice(String.valueOf(product.getRetailPrice()));
            productEntryVO.setSkuCd(product.getSkuCd());
            productEntryVO.setTitle(product.getTitle());
            productEntryVO.setWarNum("1000");
            result.add(productEntryVO);
        }
        return CommonPage.restPage(result, productPage);
    }

    @Override
    public CommonPage<ProductEntryVO> searchProductByTitle(SearchProductVO vo) {
        Page<ProProduct> productPage = PageHelper.startPage(vo.getPageNum(), vo.getPageSize()).doSelectPage(() -> {
            ProProductExample proProductExample = new ProProductExample();
            ProProductExample.Criteria criteria = proProductExample.createCriteria();
            criteria.andTitleLike("%" + vo.getTitle() + "%");
            criteria.andManIdEqualTo(vo.getManId());
            proProductMapper.selectByExample(proProductExample);
        });

        List<ProductEntryVO> result = new ArrayList<>();
        for (ProProduct product : productPage.getResult()) {
            ProductEntryVO productEntryVO = new ProductEntryVO();
            productEntryVO.setProId(product.getProId());
            productEntryVO.setRetailPrice(product.getRetailPrice().toString());
            productEntryVO.setSkuCd(product.getSkuCd());
            productEntryVO.setTitle(product.getTitle());
            productEntryVO.setWarNum("1000");
            result.add(productEntryVO);
        }
        return CommonPage.restPage(result, productPage);
    }

    @Override
    public boolean updateProductInfo(ProductUpdateVO vo) {
        // 更新商品信息
        ProProductExample proProductExample = new ProProductExample();
        ProProductExample.Criteria criteria_pro = proProductExample.createCriteria();
        criteria_pro.andProIdEqualTo(vo.getProId());
        ProProduct proProduct = new ProProduct();
        proProduct.setTitle(vo.getTitle());
        proProduct.setSkuCd(vo.getSkuCd());
        proProduct.setUpc(vo.getUpc());
        proProduct.setEan(vo.getEan());
        proProduct.setModel(vo.getModel());
        proProduct.setRetailPrice(new BigDecimal(vo.getRetailPrice()));
        proProduct.setWarrantyDay(vo.getWarrantyDay());
        proProduct.setLastUpdateBy(vo.getUserId());
        proProduct.setLastUpdateDate(new Date());
        proProductMapper.updateByExampleSelective(proProduct, proProductExample);

        // 更新重量等信息
        PckPackageInfoExample pckPackageInfoExample = new PckPackageInfoExample();
        PckPackageInfoExample.Criteria criteria_pck = pckPackageInfoExample.createCriteria();
        criteria_pck.andProIdEqualTo(vo.getProId());
        PckPackageInfo pckPackageInfo = new PckPackageInfo();
        pckPackageInfo.setLength(new BigDecimal(vo.getLength()));
        pckPackageInfo.setWidth(new BigDecimal(vo.getWeight()));
        pckPackageInfo.setHeight(new BigDecimal(vo.getHeight()));
        pckPackageInfo.setWeight(new BigDecimal(vo.getWeight()));
        pckPackageInfo.setLastUpdateBy(vo.getUserId());
        pckPackageInfo.setLastUpdeteDate(new Date());
        pckPackageInfoMapper.updateByExampleSelective(pckPackageInfo, pckPackageInfoExample);
        return true;
    }

    @Override
    public boolean deleteProductInfo(ProductDeleteVO vo) {
        proProductMapper.deleteProductInfoByList(vo.getProIds());
        pckPackageInfoMapper.deletePackageInfoByList(vo.getProIds());
        return false;
    }

    @Override
    public boolean addProductInfo(ProductAddVO vo) {
        ProProduct proProduct = new ProProduct();
        proProduct.setTitle(vo.getTitle());
        proProduct.setSkuCd(vo.getSkuCd());
        proProduct.setUpc(vo.getUpc());
        proProduct.setEan(vo.getEan());
        proProduct.setModel(vo.getModel());
        proProduct.setRetailPrice(new BigDecimal(vo.getRetailPrice()));
        proProduct.setWarrantyDay(vo.getWarrantyDay());
        proProduct.setCreatedBy(vo.getUserId());
        proProduct.setCreationDate(new Date());
        int proId = proProductMapper.insertSelective(proProduct);

        // 更新重量等信息
        PckPackageInfo pckPackageInfo = new PckPackageInfo();
        pckPackageInfo.setProId(proId);
        pckPackageInfo.setLength(new BigDecimal(vo.getLength()));
        pckPackageInfo.setWidth(new BigDecimal(vo.getWeight()));
        pckPackageInfo.setHeight(new BigDecimal(vo.getHeight()));
        pckPackageInfo.setWeight(new BigDecimal(vo.getWeight()));
        pckPackageInfo.setCreatedBy(vo.getUserId());
        pckPackageInfo.setCreationDate(new Date());
        pckPackageInfoMapper.insertSelective(pckPackageInfo);
        return true;
    }
}
