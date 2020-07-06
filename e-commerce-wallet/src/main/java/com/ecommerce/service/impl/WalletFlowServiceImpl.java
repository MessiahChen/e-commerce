package com.ecommerce.service.impl;

import com.ecommerce.dao.WaaWalletAccountMapper;
import com.ecommerce.dao.WtrWalletTransactionRecordMapper;
import com.ecommerce.pojo.WaaWalletAccount;
import com.ecommerce.pojo.WaaWalletAccountExample;
import com.ecommerce.pojo.WtrWalletTransactionRecord;
import com.ecommerce.pojo.WtrWalletTransactionRecordExample;
import com.ecommerce.service.WalletFlowService;
import com.ecommerce.vojo.WalletFlowRecordVO;
import com.ecommerce.vojo.WalletFlowVO;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 钱包流水服务实现类
 * Created by yousabla on 2020/7/3.
 */

@Service
public class WalletFlowServiceImpl implements WalletFlowService {
    @Resource
    WaaWalletAccountMapper waaWalletAccountMapper;

    @Resource
    WtrWalletTransactionRecordMapper wtrWalletTransactionRecordMapper;

    @Override
    public Boolean deposit(WalletFlowVO walletFlowVO) {
        WaaWalletAccountExample example = new WaaWalletAccountExample();
        example.createCriteria().andAccountNameEqualTo(walletFlowVO.getAccountName());
        List<WaaWalletAccount> accounts = waaWalletAccountMapper.selectByExample(example);
        WaaWalletAccount account = accounts.get(0);
        if (account.getPassword().equals(DigestUtils.sha1Hex(walletFlowVO.getPassword()))){
            account.setDepositingMoney(walletFlowVO.getFlow());
            waaWalletAccountMapper.updateByPrimaryKeySelective(account);
            generateTransaction(account.getBuyerId(),2,1,walletFlowVO.getFlow());
            return true;
        }else {
            generateTransaction(account.getBuyerId(),1,1,walletFlowVO.getFlow());
            return false;
        }
    }

    @Override
    public Boolean withdraw(WalletFlowVO walletFlowVO) {
        WaaWalletAccountExample example = new WaaWalletAccountExample();
        example.createCriteria().andAccountNameEqualTo(walletFlowVO.getAccountName());
        List<WaaWalletAccount> accounts = waaWalletAccountMapper.selectByExample(example);
        WaaWalletAccount account = accounts.get(0);
        if (account.getPassword().equals(DigestUtils.sha1Hex(walletFlowVO.getPassword())) && (account.getAvailableMoney().compareTo(walletFlowVO.getFlow()) > -1)){
            account.setWithdrawingMoney(walletFlowVO.getFlow());
            waaWalletAccountMapper.updateByPrimaryKeySelective(account);
            generateTransaction(account.getBuyerId(),2,2,walletFlowVO.getFlow());
            return true;
        }else {
            generateTransaction(account.getBuyerId(),2,2,walletFlowVO.getFlow());
            return false;
        }
    }

    @Override
    public List<WalletFlowRecordVO> check(String accountName) {
        int buyerId = waaWalletAccountMapper.getIdByName(accountName);
        WtrWalletTransactionRecordExample example = new WtrWalletTransactionRecordExample();
        example.createCriteria().andBuyerIdEqualTo(buyerId);
        List<WtrWalletTransactionRecord> records = wtrWalletTransactionRecordMapper.selectByExample(example);
        List<WalletFlowRecordVO> recordVOS = new ArrayList<WalletFlowRecordVO>();
        for (WtrWalletTransactionRecord record: records) {
            WalletFlowRecordVO recordVO = new WalletFlowRecordVO();
            recordVO.setBuyerId(record.getBuyerId());
            recordVO.setTransactionNumber(record.getTransactionNumber());
            recordVO.setTransactionMoney(record.getTransactionMoney());
            recordVO.setStatus(record.getStatus());
            recordVO.setCreateTime(record.getCreateTime());
            recordVOS.add(recordVO);
        }
        return recordVOS;
    }

    public int generateTransaction(Integer buyerId, int status, int transactionType,BigDecimal transactionMoney){
        //生成流水记录
        WtrWalletTransactionRecord recordVO = new WtrWalletTransactionRecord();
        recordVO.setCreateTime(new Date());
        recordVO.setBuyerId(buyerId);
        recordVO.setTransactionMoney(transactionMoney);
        recordVO.setTransactionType((byte) transactionType);
        recordVO.setTransactionNumber(generateTransactionNumber());
        recordVO.setStatus((byte) status);
        return wtrWalletTransactionRecordMapper.insertSelective(recordVO);
    }

    //生成流水号
    public static String generateTransactionNumber() {
        SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdfTime.format(new Date()).replaceAll("[[\\s-:punct:]]", "") + (int)(Math.random()*999999);
    }

    @Override
    public Boolean pay(WalletFlowVO walletFlowVO) {
        WaaWalletAccountExample example = new WaaWalletAccountExample();
        example.createCriteria().andAccountNameEqualTo(walletFlowVO.getAccountName());
        List<WaaWalletAccount> accounts = waaWalletAccountMapper.selectByExample(example);
        WaaWalletAccount account = accounts.get(0);

        if (account.getPassword().equals(DigestUtils.sha1Hex(walletFlowVO.getPassword())) && (account.getAvailableMoney().compareTo(walletFlowVO.getFlow()) > -1)) {
            account.setAvailableMoney(account.getAvailableMoney().subtract(walletFlowVO.getFlow()));
            account.setLastUpdateTime(new Date());

            waaWalletAccountMapper.updateByPrimaryKeySelective(account);
            generateTransaction(account.getBuyerId(),2,3,walletFlowVO.getFlow());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean refund(WalletFlowVO walletFlowVO) {
        WaaWalletAccountExample example = new WaaWalletAccountExample();
        example.createCriteria().andAccountNameEqualTo(walletFlowVO.getAccountName());
        List<WaaWalletAccount> accounts = waaWalletAccountMapper.selectByExample(example);
        WaaWalletAccount account = accounts.get(0);

        account.setAvailableMoney(account.getAvailableMoney().add(walletFlowVO.getFlow()));
        account.setLastUpdateTime(new Date());
        generateTransaction(account.getBuyerId(),2,4,walletFlowVO.getFlow());
        return true;
    }

}
