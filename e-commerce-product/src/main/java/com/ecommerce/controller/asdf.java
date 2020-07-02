package com.ecommerce.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class asdf {

    @ApiOperation("这是一个测试")
    @GetMapping("/a")
    public String getAA(){
        return "abcde";
    }
}
