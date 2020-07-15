package com.ecommerce;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.service.ProductImageService;
import com.ecommerce.vojo.image.GetAllProductImageVO;
import com.ecommerce.vojo.image.ProductImageVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ProjectName: e-commerce
 * @Package: com.ecommerce
 * @ClassName: ProductImageServiceTest
 * @Description: java类作用描述
 * @Author: 邱晓淋
 * @CreateDate: 2020/7/15 17:11
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProductApplication.class})
public class ProductImageServiceTest {
    @Autowired
    private ProductImageService productImageService;

    @Test
    public void testGetAllProductImage(){
        GetAllProductImageVO getAllProductImageVO = new GetAllProductImageVO();
        getAllProductImageVO.setManId(1);
        getAllProductImageVO.setPageNum(1);
        getAllProductImageVO.setPageSize(1);
        CommonPage<ProductImageVO> productImageVOCommonPage = productImageService.getAllProductImage(getAllProductImageVO);
        List<ProductImageVO> productImageVOs = productImageVOCommonPage.getList();
        Assert.assertEquals(10,(long)productImageVOs.get(0).getProId());
    }

}
