package com.ecommerce.service;

import com.ecommerce.vojo.WalletBalanceVO;

public interface GetWalletInfoService {

    WalletBalanceVO getWalletInfo(String accountName);

}
