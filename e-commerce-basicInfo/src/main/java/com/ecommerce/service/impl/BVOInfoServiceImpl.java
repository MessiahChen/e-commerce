package com.ecommerce.service.impl;

import com.ecommerce.dao.DsrDropshipperMapper;
import com.ecommerce.pojo.DsrDropshipper;
import com.ecommerce.pojo.DsrDropshipperExample;
import com.ecommerce.service.BVOInfoService;
import com.ecommerce.vojo.bvoinfo.BVOEntryVO;
import com.ecommerce.vojo.bvoinfo.BVOInfoUpdateVO;
import com.ecommerce.vojo.bvoinfo.GetBVOVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BVOInfoServiceImpl implements BVOInfoService {

    @Autowired
    DsrDropshipperMapper dsrDropshipperMapper;

    @Override
    public BVOEntryVO getBVO(GetBVOVO getBVOVO) {
        DsrDropshipperExample dsrDropshipperExample = new DsrDropshipperExample();
        DsrDropshipperExample.Criteria criteria = dsrDropshipperExample.createCriteria();
        criteria.andDsrIdEqualTo(getBVOVO.getDsrId());
        DsrDropshipper dsrDropshipper = dsrDropshipperMapper.
                selectByExample(dsrDropshipperExample).get(0);

        BVOEntryVO bvoEntryVO = new BVOEntryVO();
        bvoEntryVO.setDsrId(dsrDropshipper.getDsrId());
        bvoEntryVO.setEmail(dsrDropshipper.getEmail());
        bvoEntryVO.setName(dsrDropshipper.getName());
        bvoEntryVO.setPhoneNumber(dsrDropshipper.getPhoneNumber());

        return bvoEntryVO;
    }

    @Override
    public boolean updateBVOInfo(BVOInfoUpdateVO bvoInfoUpdateVO) {
        DsrDropshipperExample dsrDropshipperExample = new DsrDropshipperExample();
        DsrDropshipperExample.Criteria criteria = dsrDropshipperExample.createCriteria();
        criteria.andDsrIdEqualTo(bvoInfoUpdateVO.getDsrId());

        DsrDropshipper dsrDropshipper = new DsrDropshipper();
        dsrDropshipper.setDsrId(bvoInfoUpdateVO.getDsrId());
        dsrDropshipper.setEmail(bvoInfoUpdateVO.getEmail());
        dsrDropshipper.setName(bvoInfoUpdateVO.getName());
        dsrDropshipper.setPhoneNumber(bvoInfoUpdateVO.getPhoneNumber());

        dsrDropshipperMapper.updateByExampleSelective(dsrDropshipper, dsrDropshipperExample);

        return true;
    }
}
