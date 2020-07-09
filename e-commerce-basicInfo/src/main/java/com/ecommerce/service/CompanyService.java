package com.ecommerce.service;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.vojo.company.*;

public interface CompanyService {
    CommonPage<CompanyEntryVO> getCompany(GetCompanyVO getCompanyVO);

    boolean updateCompanyInfo(CompanyInfoUpdateVO companyInfoUpdateVO);
}
