package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.service.UploadImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public CommonResult<String> uploadImages(@RequestParam("file") MultipartFile file) {
        if (file == null) System.out.println("卧槽");
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        String imageUrl = uploadImages.uploadImages(file);
        System.out.println(imageUrl);
        if (!imageUrl.equals(""))
            return CommonResult.success(imageUrl, "上传成功");
        else return CommonResult.failed("上传失败");
    }

    @ApiOperation("批量上传商品主图到阿里云OSS")
    @PostMapping(value = "/uploadFile")
    public String uploadFile(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");//file是form-data中二进制字段对应的name
        System.out.println(multipartFile.getName());
        System.out.println(multipartFile.getOriginalFilename());
        System.out.println(multipartFile.getSize());
        return "123123";
    }
}
