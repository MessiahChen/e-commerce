package com.ecommerce.service;

import com.ecommerce.vojo.WalletFlowRecordVO;
import com.ecommerce.vojo.WalletFlowVO;
import com.ecommerce.vojo.WalletOrderVO;

import java.util.List;

/**
 * wallet流水服务接口
 * Created by yousabla on 2020/7/3.
 */

public interface WalletFlowService {

    /**
     * 充值
     */
    Boolean deposit(WalletFlowVO walletFlowVO);

    /**
     * 提现
     */
    Boolean withdraw(WalletFlowVO walletFlowVO);

    /**
     * 查看钱包流水
     */
    List<WalletFlowRecordVO> check(String accountName);

    /**
     * 支付
     */
    Boolean pay(WalletOrderVO WalletOrderVO);

    /**
     * 申请退款
     */
    Boolean refund(WalletOrderVO WalletOrderVO);




}
