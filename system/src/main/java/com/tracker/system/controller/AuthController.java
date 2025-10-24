package com.tracker.system.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.tracker.framework.domain.Result;
import com.tracker.system.domain.dto.auth.LoginDTO;
import com.tracker.system.domain.dto.auth.RegisterDTO;
import com.tracker.system.domain.vm.auth.UserRegisterVm;
import com.tracker.system.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "登录接口", description = "用户登录接口")
@RestController("/api/auth")
public class AuthController {


    @Resource
    private AuthService authService;

    @SaIgnore
    @PostMapping("/login")
    @Operation(summary = "登录")
    public Result<String> login(@RequestBody @Valid LoginDTO loginDTO) {
        String token = authService.login(loginDTO);
        return Result.success(token);
    }

    @SaIgnore
    @PostMapping("/register")
    @Operation(summary = "注册")
    public Result<UserRegisterVm> register(@RequestBody @Valid RegisterDTO registerDTO) {
        UserRegisterVm userRegisterVm = authService.register(registerDTO);
        return Result.success(userRegisterVm);
    }

    @GetMapping("/logout")
    @Operation(summary = "登出")
    public Result<String> logout() {
        authService.logout();
        return Result.success("退出成功！");
    }
}
