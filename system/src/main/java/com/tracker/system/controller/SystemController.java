package com.tracker.system.controller;

import com.tracker.framework.domain.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
@Tag(name = "系统管理", description = "系统相关功能接口")
public class SystemController {

    @GetMapping("/info")
    @Operation(summary = "获取系统信息", description = "获取当前系统的基本信息")
    public Result<String> getSystemInfo() {
        return Result.success("Code Tracker System v1.0.0");
    }

    @GetMapping("/health")
    @Operation(summary = "健康检查", description = "检查系统运行状态")
    public Result<String> healthCheck() {
        return Result.success("System is running normally");
    }
}