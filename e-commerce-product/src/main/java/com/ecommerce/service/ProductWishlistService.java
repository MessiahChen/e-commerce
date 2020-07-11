package com.ecommerce.service;

import com.ecommerce.vojo.wishlist.ProductWishlistVO;

import java.util.List;

public interface ProductWishlistService {

    List<ProductWishlistVO> getWishlistById(Integer dsrId);

    boolean deleteProFromWit(Integer dsrId, Integer proId);

    boolean batchDeleteProFromWit(Integer dsrId, List<Integer> proIds);
}
