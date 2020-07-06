package com.ecommerce.service;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.vojo.WalletAdminVO;
import com.ecommerce.vojo.WalletPageVO;

/**
 * 管理员-钱包流水服务接口
 * Created by yousabla on 2020/7/3.
 */

public interface WalletAdminService {
    /**
     * 获取所有待审核账单
     */
    CommonPage<WalletAdminVO> getAllFlow(WalletPageVO walletPageVO);

    /**
     * 审核
     */
    Boolean audit(String transactionNumber);
}