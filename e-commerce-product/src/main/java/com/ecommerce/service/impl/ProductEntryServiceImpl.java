package com.ecommerce.service.impl;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.dao.ProProductMapper;
import com.ecommerce.dto.SearchProductByTitleDTO;
import com.ecommerce.pojo.ProProduct;
import com.ecommerce.pojo.ProProductExample;
import com.ecommerce.service.ProductEntryService;
import com.ecommerce.vojo.ProductEntryVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductEntryServiceImpl implements ProductEntryService {

    @Autowired
    private ProProductMapper proProductMapper;

    @Override
    public CommonPage<ProductEntryVO> searchProductByTitle(SearchProductByTitleDTO dto) {
        Page<ProProduct> productPage = PageHelper.startPage(dto.getPageNum(), dto.getPageSize()).doSelectPage(() -> {
            ProProductExample proProductExample = new ProProductExample();
            ProProductExample.Criteria criteria = proProductExample.createCriteria();
            criteria.andTitleLike(dto.getTitle());
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
}
