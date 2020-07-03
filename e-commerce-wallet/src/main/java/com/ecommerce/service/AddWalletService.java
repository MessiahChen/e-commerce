package com.ecommerce.service;

import com.ecommerce.vojo.WalletAccountVO;

/**
 * 钱包注册接口
 * Created by yousabla on 2020/7/3.
 */
public interface AddWalletService {

    /**
     * 注册新的钱包
     *
     */
    Boolean addWallet(WalletAccountVO walletAccountVO);


}
