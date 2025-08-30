package com.tracker.system.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.tracker.framework.domain.Result;
import com.tracker.system.domain.dto.login.LoginDTO;
import com.tracker.system.service.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "登录接口", description = "用户登录接口")
@RestController
public class LoginController {


    @Resource
    private LoginService loginService;

    @SaIgnore
    @PostMapping("/login")
    public Result<String> login(@RequestBody @Valid LoginDTO loginDTO) {
        loginService.login(loginDTO);
        return Result.success("登录成功");
    }
}
