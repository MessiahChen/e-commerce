package com.ecommerce.dao;

import com.ecommerce.pojo.SysUserLoginLogoutLog;
import com.ecommerce.pojo.SysUserLoginLogoutLogExample;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserLoginLogoutLogMapper {
    long countByExample(SysUserLoginLogoutLogExample example);

    int deleteByExample(SysUserLoginLogoutLogExample example);

    int deleteByPrimaryKey(Integer ullId);

    int insert(SysUserLoginLogoutLog record);

    int insertSelective(SysUserLoginLogoutLog record);

    List<SysUserLoginLogoutLog> selectByExample(SysUserLoginLogoutLogExample example);

    SysUserLoginLogoutLog selectByPrimaryKey(Integer ullId);

    int updateByExampleSelective(@Param("record") SysUserLoginLogoutLog record, @Param("example") SysUserLoginLogoutLogExample example);

    int updateByExample(@Param("record") SysUserLoginLogoutLog record, @Param("example") SysUserLoginLogoutLogExample example);

    int updateByPrimaryKeySelective(SysUserLoginLogoutLog record);

    int updateByPrimaryKey(SysUserLoginLogoutLog record);
}