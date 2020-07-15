package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.service.UploadImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@Api(value = "上传图片控制器", tags = "上传图片控制器")
@RestController
@RequestMapping("/upload")
public class UploadImageController extends BaseController {

    @Autowired
    private UploadImageService uploadImages;

    @ApiOperation("批量上传商品主图到阿里云OSS")
    @PostMapping(value = "/uploadImage")
    public CommonResult<String> uploadImages(MultipartFile file) {
//        if (file == null) System.out.println("卧槽");
//        if (files.length == 0) System.out.println("卧槽");
//        System.out.println(files.length);
        String imageUrl = uploadImages.uploadImages(file);
        if (!imageUrl.equals(""))
            return CommonResult.success(imageUrl, "上传成功");
        else return CommonResult.failed("上传失败");
    }
}
