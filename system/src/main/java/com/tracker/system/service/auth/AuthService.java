package com.tracker.system.service.auth;

import com.tracker.system.domain.dto.auth.LoginDTO;
import com.tracker.system.domain.dto.auth.RegisterDTO;
import com.tracker.system.domain.vm.auth.UserRegisterVm;
import jakarta.validation.Valid;

public interface AuthService {

    /**
     * 登录
     *
     * @param loginDTO 登录参数
     */
    void login(@Valid LoginDTO loginDTO);


    /**
     * 注册
     *
     * @param registerDTO 注册参数
     * @return 注册结果
     */
    UserRegisterVm register(@Valid RegisterDTO registerDTO);
}
