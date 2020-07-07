package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Api(value = "借卖商-商品浏览模块控制器", tags = "借卖商-商品浏览模块控制器")
@RestController
@RequestMapping("/productBrowse")
public class ProductBrowseController extends BaseController {

}
