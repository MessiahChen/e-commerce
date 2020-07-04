package com.ecommerce.service.impl;

import com.ecommerce.dao.WaaWalletAccountMapper;
import com.ecommerce.pojo.WaaWalletAccount;
import com.ecommerce.service.AddWalletService;
import com.ecommerce.vojo.WalletAccountVO;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 钱包注册实现类
 * Created by yousabla on 2020/7/3.
 */

@Service
public class AddWalletServiceImpl implements AddWalletService {

    @Resource
    WaaWalletAccountMapper waaWalletAccountMapper;

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
//        walletAccount.setHoldOrderTime(walletAccountVO.getHoldOrderTime());
//        walletAccount.setIsActive("Y");
        walletAccount.setStatus((byte)7);

//        walletAccount.setActiveTime(date);
        walletAccount.setCreateTime(date);

        return waaWalletAccountMapper.insertSelective(walletAccount) == 1;
    }

}
