package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yousabla
 */
@Api(value = "数据字典维护",tags = "数据字典控制类")
@CrossOrigin
@RestController
@RequestMapping("/dataDictionary")
public class DataDictionaryController extends BaseController {

}
