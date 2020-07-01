package com.ecommerce.dao;

import com.ecommerce.pojo.SysAndorra;
import com.ecommerce.pojo.SysAndorraExample;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysAndorraMapper {
    long countByExample(SysAndorraExample example);

    int deleteByExample(SysAndorraExample example);

    int insert(SysAndorra record);

    int insertSelective(SysAndorra record);

    List<SysAndorra> selectByExample(SysAndorraExample example);

    int updateByExampleSelective(@Param("record") SysAndorra record, @Param("example") SysAndorraExample example);

    int updateByExample(@Param("record") SysAndorra record, @Param("example") SysAndorraExample example);
}