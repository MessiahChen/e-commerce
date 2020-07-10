package com.ecommerce.service.impl;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.dao.BrdBrandMapper;
import com.ecommerce.pojo.BrdBrand;
import com.ecommerce.pojo.BrdBrandExample;
import com.ecommerce.service.BrandService;
import com.ecommerce.vojo.brand.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrdBrandMapper brdBrandMapper;


    @Override
    public CommonPage<BrandEntryVO> getAllBrand(GetAllBrandVO getAllBrandVO) {
        Page<BrdBrand> brandPage = PageHelper.startPage(getAllBrandVO.getPageNum(),
                getAllBrandVO.getPageSize()).doSelectPage(() -> {
            BrdBrandExample brdBrandExample = new BrdBrandExample();
            BrdBrandExample.Criteria criteria = brdBrandExample.createCriteria();
            criteria.andBrdIdEqualTo(getAllBrandVO.getBrdId());
            brdBrandMapper.selectByExample(brdBrandExample);
        });

        List<BrandEntryVO> result = new ArrayList<>();
        for (BrdBrand brand : brandPage.getResult()) {
            BrandEntryVO brandEntryVO = new BrandEntryVO();
            brandEntryVO.setNameEn(brand.getNameEn());
            brandEntryVO.setRemark(brand.getRemark());
            result.add(brandEntryVO);
        }
        return CommonPage.restPage(result, brandPage);
    }

    @Override
    public boolean addBrandInfo(BrandAddVO brandAddVO) {
        BrdBrand brdBrand = new BrdBrand();
        brdBrand.setBrdId(brandAddVO.getBrdId());
        brdBrand.setCallCnt(brandAddVO.getCallCnt());
        brdBrand.setCreatedBy(brandAddVO.getCreatedBy());
        brdBrand.setCreationDate(brandAddVO.getCreationDate());
        brdBrand.setLastUpdateBy(brandAddVO.getLastUpdateBy());
        brdBrand.setLastUpdateDate(brandAddVO.getLastUpdateDate());
        brdBrand.setManId(brandAddVO.getManId());
        brdBrand.setNameCn(brandAddVO.getNameCn());
        brdBrand.setNameEn(brandAddVO.getNameEn());
        brdBrand.setRemark(brandAddVO.getRemark());
        brdBrand.setStsCd(brandAddVO.getStsCd());
        brdBrandMapper.insertSelective(brdBrand);
        return true;
    }


    @Override
    public boolean updateBrandInfo(BrandUpdateVO brandUpdateVO) {
        BrdBrandExample brdBrandExample = new BrdBrandExample();
        BrdBrandExample.Criteria criteria = brdBrandExample.createCriteria();
        criteria.andBrdIdEqualTo(brandUpdateVO.getBrdId());

        BrdBrand brdBrand = new BrdBrand();
        brdBrand.setBrdId(brandUpdateVO.getBrdId());
        brdBrand.setCallCnt(brandUpdateVO.getCallCnt());
        brdBrand.setCreatedBy(brandUpdateVO.getCreatedBy());
        brdBrand.setCreationDate(brandUpdateVO.getCreationDate());
        brdBrand.setLastUpdateBy(brandUpdateVO.getLastUpdateBy());
        brdBrand.setLastUpdateDate(brandUpdateVO.getLastUpdateDate());
        brdBrand.setManId(brandUpdateVO.getManId());
        brdBrand.setNameCn(brandUpdateVO.getNameCn());
        brdBrand.setNameEn(brandUpdateVO.getNameEn());
        brdBrand.setRemark(brandUpdateVO.getRemark());
        brdBrand.setStsCd(brandUpdateVO.getStsCd());
        
        brdBrandMapper.updateByExample(brdBrand,brdBrandExample);
        return true;
    }

    @Override
    public boolean deleteBrandInfo(Integer brdId) {
        brdBrandMapper.deleteByPrimaryKey(brdId);
        return true;
    }
}
