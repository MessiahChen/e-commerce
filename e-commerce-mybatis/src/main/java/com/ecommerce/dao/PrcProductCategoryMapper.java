package com.ecommerce.dao;

import com.ecommerce.pojo.PrcProductCategory;
import com.ecommerce.pojo.PrcProductCategoryExample;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PrcProductCategoryMapper {
    long countByExample(PrcProductCategoryExample example);

    int deleteByExample(PrcProductCategoryExample example);

    int deleteByPrimaryKey(Integer prcId);

    int insert(PrcProductCategory record);

    int insertSelective(PrcProductCategory record);

    List<PrcProductCategory> selectByExample(PrcProductCategoryExample example);

    PrcProductCategory selectByPrimaryKey(Integer prcId);

    int updateByExampleSelective(@Param("record") PrcProductCategory record, @Param("example") PrcProductCategoryExample example);

    int updateByExample(@Param("record") PrcProductCategory record, @Param("example") PrcProductCategoryExample example);

    int updateByPrimaryKeySelective(PrcProductCategory record);

    int updateByPrimaryKey(PrcProductCategory record);
}