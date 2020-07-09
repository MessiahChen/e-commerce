package com.ecommerce.service.impl;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.dao.BrdBrandMapper;
import com.ecommerce.service.BrandService;
import com.ecommerce.vojo.brand.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrdBrandMapper brdBrandMapper;


    @Override
    public CommonPage<BrandEntryVO> getAllBrand(GetAllBrandVO getAllBrandVO) {
        return null;
    }

    @Override
    public boolean addBrandInfo(BrandAddVO brandAddVO) {
        return false;
    }

    @Override
    public BrandAddVO getBrandInfoWhenUpdate(Integer brdId) {
        return null;
    }

    @Override
    public boolean updateBrandInfo(BrandUpdateVO brandUpdateVO) {
        return false;
    }

    @Override
    public boolean deleteBrandInfo(Integer brdId) {
        return false;
    }
}
