package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonPage;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.base.ResultCode;
import com.ecommerce.service.ProductBrowseService;
import com.ecommerce.vojo.browse.OperateWishlistVO;
import com.ecommerce.vojo.browse.ProductBrowseWithCatVO;
import com.ecommerce.vojo.browse.ProductDetailVO;
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

    @ApiOperation("根据商品ID获取到该商品的详细信息")
    @GetMapping("/getProductInfoDetailById")
    public CommonResult<ProductDetailVO> getProductInfoDetailById(@RequestParam("dsrId") Integer dsrId, @RequestParam("proId") Integer proId) {
        ProductDetailVO result = productBrowseService.getProductInfoDetailById(dsrId, proId);
        return CommonResult.success(result, "获得商品详情成功");
    }

    @ApiOperation("添加到心愿清单中")
    @PatchMapping("/addToWishlist")
    public CommonResult addToWishlist(OperateWishlistVO operateWishlistVO) {
        if (productBrowseService.addToWishlist(operateWishlistVO)) {
            return CommonResult.success("添加成功");
        } else {
            return CommonResult.success("添加失败");
        }
    }

    @ApiOperation("从心愿清单中删除")
    @PatchMapping("/deleteFromWishlist")
    public CommonResult deleteFromWishlist(OperateWishlistVO operateWishlistVO) {
        if (productBrowseService.deleteFromWishlist(operateWishlistVO))
            return CommonResult.success("删除成功");
        else return CommonResult.success("删除失败");
    }
}
