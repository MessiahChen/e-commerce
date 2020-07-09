package com.ecommerce.service;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.vojo.store.*;

public interface StoreService {
    CommonPage<StoreEntryVO> getStore(GetStoreVO getStoreVO);

    boolean addStore(StoreAddVO storeAddVO);
}
