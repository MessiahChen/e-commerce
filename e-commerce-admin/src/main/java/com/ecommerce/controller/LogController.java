package com.ecommerce.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.captcha.LineCaptcha;
import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.pojo.SysUser;
import com.ecommerce.security.component.DynamicSecurityMetadataSource;
import com.ecommerce.service.RedisService;
import com.ecommerce.service.UserService;
import com.ecommerce.vojo.LoginVO;
import com.ecommerce.vojo.RegisterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@Controller
@Api(value = "用户信息维护", tags = "用户控制类")
@RequestMapping("/log")
public class LogController extends BaseController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Resource
    private UserService userService;
    @Resource
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;
    @Resource
    private RedisService redisService;

    @ApiOperation(value = "用户注册")
    @PutMapping(value = "/register")
    @ResponseBody
    public CommonResult<SysUser> register(@RequestBody RegisterVO registerVO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BusinessException().newInstance(this.getErrorResponse(result), registerVO.toString());
        }
        SysUser umsAdmin = userService.register(registerVO);
        if (umsAdmin == null) {
            CommonResult.failed();
        }
        dynamicSecurityMetadataSource.clearDataSource();
        return CommonResult.success(umsAdmin, "注册成功！");
    }

    @ApiOperation(value = "登录以后返回token")
    @PostMapping(value = "/login")
    @ResponseBody
    public CommonResult<Map<String, String>> login(@RequestBody LoginVO loginVO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BusinessException().newInstance(this.getErrorResponse(result), loginVO.toString());
        }
        String token = userService.login(loginVO.getUsername(), loginVO.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap, "");
    }

    @ApiOperation(value = "生成图像验证码")
    @GetMapping(value = "/generateValidateCode")
    public void generateValidateCode(HttpServletResponse response) {
        //设置response响应
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //把图形验证码凭证放入cookie中
        String tokenId = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("imgCodeToken", tokenId);
        cookie.setPath("/");
        response.addCookie(cookie);

        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100, 4, 150);

        int time = 60;

        //把凭证对应的验证码信息保存到reids（可从redis中获取）
        redisService.set(tokenId, lineCaptcha.getCode(), time);
        try {
            //输出浏览器
            OutputStream out = response.getOutputStream();
            lineCaptcha.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("输出错误");
        }
    }

    @ApiOperation(value = "验证图像验证码")
    @GetMapping(value = "/verifyValidateCode")
    public void verifyValidateCode(HttpServletRequest request) {

    }
}
