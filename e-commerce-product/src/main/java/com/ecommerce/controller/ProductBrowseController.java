package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonPage;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.base.ResultCode;
import com.ecommerce.service.ProductBrowseService;
import com.ecommerce.vojo.browse.ProductBrowseWithCatVO;
import com.ecommerce.vojo.entry.ProductEntryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Api(value = "借卖商-商品浏览模块控制器", tags = "借卖商-商品浏览模块控制器")
@RestController
@RequestMapping("/productBrowse")
public class ProductBrowseController extends BaseController {

    @Autowired
    private ProductBrowseService productBrowseService;

    @ApiOperation("找到所有可以购买的商品")
    @GetMapping("/getAllProductWithStatD")
    public CommonResult<List<ProductBrowseWithCatVO>> getAllProductWithStatD() {
        List<ProductBrowseWithCatVO> result = productBrowseService.getAllProductWithStatD();
        if (!result.isEmpty()) {
            return CommonResult.success(result, "获得所有商品成功");
        } else {
            return CommonResult.failed(ResultCode.THINGS_NOT_FOUND);
        }

    }

}
