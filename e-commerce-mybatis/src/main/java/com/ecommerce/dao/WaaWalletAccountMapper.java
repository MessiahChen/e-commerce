package com.ecommerce.dao;

import com.ecommerce.pojo.WaaWalletAccount;
import com.ecommerce.pojo.WaaWalletAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 新增ID查询
 * Edit by yousabla on 2020/7/3.
 */

public interface WaaWalletAccountMapper {
    long countByExample(WaaWalletAccountExample example);

    int deleteByExample(WaaWalletAccountExample example);

    int deleteByPrimaryKey(Integer buyerId);

    int insert(WaaWalletAccount record);

    int insertSelective(WaaWalletAccount record);

    List<WaaWalletAccount> selectByExample(WaaWalletAccountExample example);

    WaaWalletAccount selectByPrimaryKey(Integer buyerId);

    int updateByExampleSelective(@Param("record") WaaWalletAccount record, @Param("example") WaaWalletAccountExample example);

    int updateByExample(@Param("record") WaaWalletAccount record, @Param("example") WaaWalletAccountExample example);

    int updateByPrimaryKeySelective(WaaWalletAccount record);

    int updateByPrimaryKey(WaaWalletAccount record);

    @Select("select buyer_id from waa_wallet_account where account_name = #{accountName}")
    int getBuyerIdByUserName(@Param("accountName") String accountName);
}