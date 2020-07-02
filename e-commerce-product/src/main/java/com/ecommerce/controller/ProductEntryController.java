package com.ecommerce.controller;

import com.ecommerce.common.base.*;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.common.validationGroup.InsertGroup;
import com.ecommerce.dto.SearchProductByTitleDTO;
import com.ecommerce.pojo.SysMenu;
import com.ecommerce.service.ProductEntryService;
import com.ecommerce.vojo.ProductEntryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Api(value = "品牌商-商品录入模块控制器")
@RestController
@RequestMapping("/productEntry")
public class ProductEntryController extends BaseController {

    @Autowired
    private ProductEntryService productEntryService;

    @ApiOperation("通过商品标题模糊匹配商品")
    @PostMapping("/searchProductByTitle")
    public CommonResult<CommonPage> searchProductByTitle(@RequestBody SearchProductByTitleDTO searchProductByTitleDTO) {
        CommonPage result = productEntryService.searchProductByTitle(searchProductByTitleDTO);
        if (!result.getList().isEmpty()) {
            return CommonResult.success(result, "匹配成功");
        } else {
            return CommonResult.failed(ResultCode.THINGS_NOT_FOUND);
        }
    }
}
