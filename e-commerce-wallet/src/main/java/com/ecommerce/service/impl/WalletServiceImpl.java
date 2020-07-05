package com.ecommerce.service.impl;

import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.dao.WaaWalletAccountMapper;
import com.ecommerce.pojo.WaaWalletAccount;
import com.ecommerce.service.RedisService;
import com.ecommerce.service.WalletService;
import com.ecommerce.vojo.WalletAccountVO;
import com.ecommerce.vojo.WalletBalanceVO;
import com.ecommerce.vojo.WalletPasswordVO;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * wallet服务实现类
 * Created by yousabla on 2020/7/3.
 */

@Service
public class WalletServiceImpl implements WalletService {

    @Resource
    WaaWalletAccountMapper waaWalletAccountMapper;

    @Resource
    RedisService redisService;

    @Override
    public Boolean addWallet(WalletAccountVO walletAccountVO) {
        Date date = new Date();

        WaaWalletAccount walletAccount = new WaaWalletAccount();
        walletAccount.setAccountName(walletAccountVO.getAccountName());
//        walletAccount.setAccountType(walletAccountVO.getAccountType());
//        walletAccount.setAutoPayStatus(walletAccountVO.getAutoPayStatus());
//        walletAccount.setCreateBy(walletAccountVO.getCreateBy());
        //SHA加密
        walletAccount.setPassword(DigestUtils.sha1Hex(walletAccountVO.getPassword()));
        walletAccount.setEmail(walletAccountVO.getEmail());
        walletAccount.setStatus((byte)7);

        walletAccount.setAvailableMoney(BigDecimal.ZERO);
        walletAccount.setDepositingMoney(BigDecimal.ZERO);
        walletAccount.setWithdrawingMoney(BigDecimal.ZERO);

        walletAccount.setCreateTime(date);

        return waaWalletAccountMapper.insertSelective(walletAccount) == 1;
    }



    public WalletBalanceVO getWalletInfo(String accountName){
        WaaWalletAccount walletInfo = waaWalletAccountMapper.selectByPrimaryKey(waaWalletAccountMapper.getIdByName(accountName));
        if (walletInfo != null){
//            redisService.set(accountName+"Balance",walletInfo.getAvailableMoney());
            WalletBalanceVO balanceVO = new WalletBalanceVO();
            balanceVO.setCurrency(walletInfo.getCurrency());
            balanceVO.setBuyerId(walletInfo.getBuyerId());
            balanceVO.setAvailableMoney(walletInfo.getAvailableMoney());
            balanceVO.setDepositingMoney(walletInfo.getDepositingMoney());
            balanceVO.setWithdrawingMoney(walletInfo.getWithdrawingMoney());
            return balanceVO;
        }else {
            throw BusinessException.USERNAME_NOT_EXISTS;
        }
    }

    @Override
    public Boolean changePassword(WalletPasswordVO passwordVO) {
        WaaWalletAccount account = waaWalletAccountMapper.selectByPrimaryKey(waaWalletAccountMapper.getIdByName(passwordVO.getAccountName()));
        if (DigestUtils.sha1Hex(passwordVO.getOldPassword()).equals(account.getPassword())){
            account.setPassword(DigestUtils.sha1Hex(passwordVO.getNewPassword()));
            waaWalletAccountMapper.updateByPrimaryKeySelective(account);
            return true;
        }else {
            return false;
        }
    }

}
