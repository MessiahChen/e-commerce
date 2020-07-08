package com.ecommerce.service;

import com.ecommerce.pojo.SysMenu;

import java.util.List;

/**
 * @author 马洪升 2020年7月1日16:32:19
 */
public interface AdminService {

    /**
     * 注册新的钱包
     */
    Boolean addWallet(WalletAccountVO walletAccountVO);

    /**
     * 获取钱包信息
     * @return
     */
    List<WalletBalanceVO> getWalletInfo(String accountName);

    /**
     * 更改支付密码
     */
    Boolean changePassword(WalletPasswordVO passwordVO);
}
