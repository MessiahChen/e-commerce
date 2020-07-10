package com.ecommerce.service.impl;

import com.ecommerce.dao.ManManufacturerMapper;
import com.ecommerce.pojo.ManManufacturer;
import com.ecommerce.pojo.ManManufacturerExample;
import com.ecommerce.service.CompanyService;
import com.ecommerce.vojo.company.CompanyEntryVO;
import com.ecommerce.vojo.company.CompanyInfoUpdateVO;
import com.ecommerce.vojo.company.GetCompanyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    ManManufacturerMapper manManufacturerMapper;

    @Override
    public CompanyEntryVO getCompany(GetCompanyVO getCompanyVO) {
        ManManufacturerExample manManufacturerExample = new ManManufacturerExample();
        ManManufacturerExample.Criteria criteria = manManufacturerExample.createCriteria();
        criteria.andManIdEqualTo(getCompanyVO.getManId());
        ManManufacturer manManufacturer = manManufacturerMapper.
                selectByExample(manManufacturerExample).get(0);

        CompanyEntryVO companyEntryVO = new CompanyEntryVO();
        companyEntryVO.setGmcReportType(manManufacturer.getGmcReportType());
        companyEntryVO.setGmcReportUrl(manManufacturer.getGmcReportUrl());
        companyEntryVO.setManId(manManufacturer.getManId());
        companyEntryVO.setNameCn(manManufacturer.getNameCn());
        companyEntryVO.setNameEn(manManufacturer.getNameEn());

        return companyEntryVO;
    }

    @Override
    public boolean updateCompanyInfo(CompanyInfoUpdateVO companyInfoUpdateVO) {
        ManManufacturerExample manManufacturerExample = new ManManufacturerExample();
        ManManufacturerExample.Criteria criteria = manManufacturerExample.createCriteria();
        criteria.andManIdEqualTo(companyInfoUpdateVO.getManId());

        ManManufacturer manManufacturer = new ManManufacturer();
        manManufacturer.setGmcReportType(companyInfoUpdateVO.getGmcReportType());
        manManufacturer.setGmcReportUrl(companyInfoUpdateVO.getGmcReportUrl());
        manManufacturer.setManId(companyInfoUpdateVO.getManId());
        manManufacturer.setNameCn(companyInfoUpdateVO.getNameCn());
        manManufacturer.setNameEn(companyInfoUpdateVO.getNameEn());
        manManufacturer.setCallCnt(companyInfoUpdateVO.getCallCnt());
        manManufacturer.setCreatedBy(companyInfoUpdateVO.getCreatedBy());
        manManufacturer.setCreationDate(companyInfoUpdateVO.getCreationDate());
        manManufacturer.setDescription(companyInfoUpdateVO.getDescription());
        manManufacturer.setLastUpdateBy(companyInfoUpdateVO.getLastUpdateBy());
        manManufacturer.setLastUpdateDate(companyInfoUpdateVO.getLastUpdateDate());
        manManufacturer.setRemark(companyInfoUpdateVO.getRemark());
        manManufacturer.setStsCd(companyInfoUpdateVO.getStsCd());

        manManufacturerMapper.updateByExampleSelective(manManufacturer, manManufacturerExample);
        return true;
    }
}
