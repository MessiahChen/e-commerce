package com.ecommerce.service;

import com.ecommerce.vojo.WalletAccountVO;
import com.ecommerce.vojo.WalletBalanceVO;

/**
 * wallet服务接口
 * Created by yousabla on 2020/7/3.
 */
public interface WalletService {

    /**
     * 注册新的钱包
     */
    Boolean addWallet(WalletAccountVO walletAccountVO);

    /**
     * 获取钱包信息
     */
    WalletBalanceVO getWalletInfo(String accountName);


}
