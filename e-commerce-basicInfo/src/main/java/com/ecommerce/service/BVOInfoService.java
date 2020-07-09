package com.ecommerce.service;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.vojo.bvoinfo.*;

public interface BVOInfoService {
    CommonPage<BVOEntryVO> getBVO(GetBVOVO getBVOVO);

    boolean updateBVOInfo(BVOInfoUpdateVO bvoInfoUpdateVO);

}
