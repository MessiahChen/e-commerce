package com.ecommerce.service.impl;

import com.ecommerce.dao.ImgImageMapper;
import com.ecommerce.dao.PrcProductCategoryMapper;
import com.ecommerce.dao.ProProductMapper;
import com.ecommerce.pojo.ProProduct;
import com.ecommerce.pojo.ProProductExample;
import com.ecommerce.service.ProductBrowseService;
import com.ecommerce.vojo.browse.ProductBrowseWithCatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductBrowseServiceImpl implements ProductBrowseService {

    @Autowired
    private ProProductMapper proProductMapper;

    @Autowired
    private PrcProductCategoryMapper prcProductCategoryMapper;

    @Autowired
    private ImgImageMapper imgImageMapper;

    @Override
    public List<ProductBrowseWithCatVO> getAllProductWithStatD() {
        ProProductExample proProductExample = new ProProductExample();
        ProProductExample.Criteria criteria_pro = proProductExample.createCriteria();
        criteria_pro.andStsCdEqualTo("c");

        List<ProProduct> proProducts = proProductMapper.selectByExample(proProductExample);

//        for (ProProduct pro : proProducts) {
//
//
//            prcProductCategoryMapper.selectByExample();
//        }

        return null;
    }
}
