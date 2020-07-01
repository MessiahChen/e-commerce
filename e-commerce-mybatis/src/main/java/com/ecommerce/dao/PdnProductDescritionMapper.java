package com.ecommerce.dao;

import com.ecommerce.pojo.PdnProductDescrition;
import com.ecommerce.pojo.PdnProductDescritionExample;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PdnProductDescritionMapper {
    long countByExample(PdnProductDescritionExample example);

    int deleteByExample(PdnProductDescritionExample example);

    int deleteByPrimaryKey(Integer pdnId);

    int insert(PdnProductDescrition record);

    int insertSelective(PdnProductDescrition record);

    List<PdnProductDescrition> selectByExampleWithBLOBs(PdnProductDescritionExample example);

    List<PdnProductDescrition> selectByExample(PdnProductDescritionExample example);

    PdnProductDescrition selectByPrimaryKey(Integer pdnId);

    int updateByExampleSelective(@Param("record") PdnProductDescrition record, @Param("example") PdnProductDescritionExample example);

    int updateByExampleWithBLOBs(@Param("record") PdnProductDescrition record, @Param("example") PdnProductDescritionExample example);

    int updateByExample(@Param("record") PdnProductDescrition record, @Param("example") PdnProductDescritionExample example);

    int updateByPrimaryKeySelective(PdnProductDescrition record);

    int updateByPrimaryKeyWithBLOBs(PdnProductDescrition record);

    int updateByPrimaryKey(PdnProductDescrition record);
}