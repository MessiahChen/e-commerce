package com.ecommerce.dao;

import com.ecommerce.pojo.UllUserLoginLogoutLog;
import com.ecommerce.pojo.UllUserLoginLogoutLogExample;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UllUserLoginLogoutLogMapper {
    long countByExample(UllUserLoginLogoutLogExample example);

    int deleteByExample(UllUserLoginLogoutLogExample example);

    int deleteByPrimaryKey(Integer ullId);

    int insert(UllUserLoginLogoutLog record);

    int insertSelective(UllUserLoginLogoutLog record);

    List<UllUserLoginLogoutLog> selectByExample(UllUserLoginLogoutLogExample example);

    UllUserLoginLogoutLog selectByPrimaryKey(Integer ullId);

    int updateByExampleSelective(@Param("record") UllUserLoginLogoutLog record, @Param("example") UllUserLoginLogoutLogExample example);

    int updateByExample(@Param("record") UllUserLoginLogoutLog record, @Param("example") UllUserLoginLogoutLogExample example);

    int updateByPrimaryKeySelective(UllUserLoginLogoutLog record);

    int updateByPrimaryKey(UllUserLoginLogoutLog record);
}