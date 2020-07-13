package com.ecommerce.service;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.vojo.*;

import java.util.List;

/**
 * @author 90707
 */
public interface ParameterService {
    boolean add(AddParameterVO addParameterVO);

    CommonPage<ParInfoVO> getAllParInfo(ParPageVO parPageVO);

    CommonPage<ParInfoVO> searchPar(SearchParVO searchParVO);

    ParInfoVO getParWhenUpdate(Integer parId);

    Boolean updatePar(UpdateParVO updateParVO);

    Boolean deletePar(Integer parId);

    Boolean batchDeletePar(List<Integer> parIds);
}
