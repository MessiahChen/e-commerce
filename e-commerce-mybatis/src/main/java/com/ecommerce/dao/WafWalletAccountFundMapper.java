package com.ecommerce.dao;

import com.ecommerce.pojo.WafWalletAccountFund;
import com.ecommerce.pojo.WafWalletAccountFundExample;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WafWalletAccountFundMapper {
    long countByExample(WafWalletAccountFundExample example);

    int deleteByExample(WafWalletAccountFundExample example);

    int deleteByPrimaryKey(Integer buyerId);

    int insert(WafWalletAccountFund record);

    int insertSelective(WafWalletAccountFund record);

    List<WafWalletAccountFund> selectByExample(WafWalletAccountFundExample example);

    WafWalletAccountFund selectByPrimaryKey(Integer buyerId);

    int updateByExampleSelective(@Param("record") WafWalletAccountFund record, @Param("example") WafWalletAccountFundExample example);

    int updateByExample(@Param("record") WafWalletAccountFund record, @Param("example") WafWalletAccountFundExample example);

    int updateByPrimaryKeySelective(WafWalletAccountFund record);

    int updateByPrimaryKey(WafWalletAccountFund record);
}