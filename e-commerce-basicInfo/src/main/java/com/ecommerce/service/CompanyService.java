package com.ecommerce.service;

import com.ecommerce.vojo.company.*;

public interface CompanyService {
    CompanyEntryVO getCompany(GetCompanyVO getCompanyVO);

    boolean updateCompanyInfo(CompanyInfoUpdateVO companyInfoUpdateVO);
}
