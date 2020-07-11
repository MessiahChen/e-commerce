package com.ecommerce.service.impl;

import com.ecommerce.dao.WitWishlistMapper;
import com.ecommerce.pojo.WitWishlist;
import com.ecommerce.pojo.WitWishlistExample;
import com.ecommerce.service.ProductWishlistService;
import com.ecommerce.vojo.wishlist.ProductWishlistVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductWishlistServiceImpl implements ProductWishlistService {

    @Autowired
    private WitWishlistMapper witWishlistMapper;

    @Override
    public List<ProductWishlistVO> getWishlistById(Integer dsrId) {
        List<ProductWishlistVO> result = new ArrayList<>();



        return result;
    }

    @Override
    public boolean deleteProFromWit(Integer dsrId, Integer proId) {
        WitWishlistExample witWishlistExample = new WitWishlistExample();
        WitWishlistExample.Criteria criteria = witWishlistExample.createCriteria();
        criteria.andDsrIdEqualTo(dsrId);
        criteria.andProIdEqualTo(proId);

        int result = witWishlistMapper.deleteByExample(witWishlistExample);
        return result == 1;
    }

    @Override
    public boolean batchDeleteProFromWit(Integer dsrId, List<Integer> proIds) {


        return false;
    }


}
