package com.ecommerce.service.impl;

import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.dao.WaaWalletAccountFundMapper;
import com.ecommerce.dao.WaaWalletAccountMapper;
import com.ecommerce.pojo.WaaWalletAccountFund;
import com.ecommerce.service.GetWalletInfoService;
import com.ecommerce.service.RedisService;
import com.ecommerce.vojo.WalletBalanceVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GetWalletInfoServiceImpl implements GetWalletInfoService {

    @Resource
    WaaWalletAccountFundMapper waaWalletAccountFundMapper;

    @Resource
    WaaWalletAccountMapper waaWalletAccountMapper;

    @Resource
    RedisService redisService;

    public WalletBalanceVO getWalletInfo(String accountName){
        WaaWalletAccountFund walletInfo = waaWalletAccountFundMapper.selectByPrimaryKey(waaWalletAccountMapper.getBuyerIdByUserName(accountName));
        if (walletInfo != null){
            redisService.set(accountName+"Balance",walletInfo.getAvailableMoney());
            WalletBalanceVO balance = new WalletBalanceVO();
            balance.setAvailableMoney(walletInfo.getAvailableMoney());
            balance.setBuyerId(walletInfo.getBuyerId());
            balance.setDepositingMoney(walletInfo.getDepositingMoney());
            balance.setWithdrawingMoney(walletInfo.getWithdrawingMoney());
            balance.setCurrency(walletInfo.getCurrency());
            return balance;
        }else {
            throw BusinessException.USERNAME_NOT_EXISTS;
        }
    }
}
