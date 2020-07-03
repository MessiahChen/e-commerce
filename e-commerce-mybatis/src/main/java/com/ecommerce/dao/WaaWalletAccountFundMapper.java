package com.ecommerce.dao;

import com.ecommerce.pojo.WaaWalletAccountFund;
import com.ecommerce.pojo.WaaWalletAccountFundExample;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WaaWalletAccountFundMapper {
    long countByExample(WaaWalletAccountFundExample example);

    int deleteByExample(WaaWalletAccountFundExample example);

    int deleteByPrimaryKey(Integer buyerId);

    int insert(WaaWalletAccountFund record);

    int insertSelective(WaaWalletAccountFund record);

    List<WaaWalletAccountFund> selectByExample(WaaWalletAccountFundExample example);

    WaaWalletAccountFund selectByPrimaryKey(Integer buyerId);

    int updateByExampleSelective(@Param("record") WaaWalletAccountFund record, @Param("example") WaaWalletAccountFundExample example);

    int updateByExample(@Param("record") WaaWalletAccountFund record, @Param("example") WaaWalletAccountFundExample example);

    int updateByPrimaryKeySelective(WaaWalletAccountFund record);

    int updateByPrimaryKey(WaaWalletAccountFund record);
}