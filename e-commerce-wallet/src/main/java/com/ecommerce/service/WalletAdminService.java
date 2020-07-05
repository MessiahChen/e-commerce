package com.ecommerce.service;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.vojo.WalletAdminVO;
import com.ecommerce.vojo.WalletPageVO;

public interface WalletAdminService {

    CommonPage<WalletAdminVO> getAllFlow(WalletPageVO walletPageVO);
}
