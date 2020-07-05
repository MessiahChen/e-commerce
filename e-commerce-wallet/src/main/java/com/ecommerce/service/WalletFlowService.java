package com.ecommerce.service;

import com.ecommerce.vojo.WalletFlowRecordVO;
import com.ecommerce.vojo.WalletFlowVO;

import java.util.List;

public interface WalletFlowService {

    Boolean deposit(WalletFlowVO walletFlowVO);

    Boolean withdraw(WalletFlowVO walletFlowVO);

    List<WalletFlowRecordVO> check(String accountName);
}
