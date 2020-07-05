package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonPage;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.base.ResultCode;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.common.validationGroup.DeleteGroup;
import com.ecommerce.common.validationGroup.InsertGroup;
import com.ecommerce.common.validationGroup.UpdateGroup;
import com.ecommerce.service.ProductImageService;
import com.ecommerce.vojo.entry.GetAllProductVO;
import com.ecommerce.vojo.entry.ProductEntryVO;
import com.ecommerce.vojo.image.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@Api(value = "品牌商-商品主图模块控制器", tags = "品牌商-商品主图模块控制器")
@RestController
@RequestMapping("/productImage")
public class ProductImageController extends BaseController {

    @Autowired
    private ProductImageService productImageService;

    @ApiOperation("找到该品牌所有商品主图")
    @PostMapping("/getAllProductImage")
    public CommonResult<CommonPage<ProductImageVO>> getAllProductImage(@RequestBody GetAllProductImageVO getAllProductImageVO) {
        CommonPage<ProductImageVO> result = productImageService.getAllProductImage(getAllProductImageVO);
        if (!result.getList().isEmpty()) {
            return CommonResult.success(result, "匹配成功");
        } else {
            return CommonResult.failed(ResultCode.THINGS_NOT_FOUND);
        }
    }

    @ApiOperation("通过商品标题模糊匹配商品")
    @GetMapping("/searchProductImageByTitle")
    public CommonResult<CommonPage> searchProductImageByTitle(@RequestParam(value = "title") String title, @RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        CommonPage result = productImageService.searchProductImageByTitle(title, pageNum, pageSize);
        if (!result.getList().isEmpty()) {
            return CommonResult.success(result, "匹配成功");
        } else {
            return CommonResult.failed(ResultCode.THINGS_NOT_FOUND);
        }
    }

    @ApiOperation("通过商品ID更新商品主图")
    @PatchMapping("/updateProductImage")
    public CommonResult updateProductImage(@Validated({UpdateGroup.class}) @RequestBody ProductCategoryUpdateVO productCategoryUpdateVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw BusinessException.UPDATE_FAIL.newInstance(this.getErrorResponse(bindingResult), productCategoryUpdateVO.toString());
        } else {
            if (productImageService.updateProductImage(productCategoryUpdateVO)) {
                return CommonResult.success("更新成功");
            } else {
                throw BusinessException.UPDATE_FAIL;
            }
        }
    }

    @ApiOperation("通过商品ID删除商品主图")
    @DeleteMapping("/deleteProductImage")
    public CommonResult deleteProductImage(@Validated({DeleteGroup.class}) @RequestBody ProductImageDeleteVO productImageDeleteVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw BusinessException.DELETE_FAIL.newInstance(this.getErrorResponse(bindingResult), productImageDeleteVO.toString());
        } else {
            if (productImageService.deleteProductImage(productImageDeleteVO)) {
                return CommonResult.success("更新成功");
            } else {
                throw BusinessException.DELETE_FAIL;
            }
        }
    }

    @ApiOperation("添加新的商品主图")
    @PutMapping("/addProductImage")
    public CommonResult addProductImage(@Validated({InsertGroup.class}) @RequestBody ProductCategoryAddVO productCategoryAddVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw BusinessException.INSERT_FAIL.newInstance(this.getErrorResponse(bindingResult), productCategoryAddVO.toString());
        } else {
            if (productImageService.addProductCategory(productCategoryAddVO)) {
                return CommonResult.success("添加成功");
            } else {
                throw BusinessException.INSERT_FAIL;
            }
        }
    }

    @ApiOperation("批量上传商品主图到阿里云OSS")
    @PostMapping(value = "/uploadImages")
    public CommonResult uploadImages(@RequestParam("files") List<MultipartFile> file) {
//        if (file == null) System.out.println("卧槽");
//        if (files.length == 0) System.out.println("卧槽");
//        System.out.println(files.length);
        List<String> imageUrls = productImageService.uploadImages(file);
        return CommonResult.success(imageUrls, "上传成功");
//        return CommonResult.success("上传成功");
    }
}