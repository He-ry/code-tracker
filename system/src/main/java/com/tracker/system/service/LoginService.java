package com.tracker.system.service;

import com.tracker.system.domain.dto.login.LoginDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Resource
    private UserService userService;


    public void login(LoginDTO loginDTO) {

    }
}
