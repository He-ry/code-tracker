package com.tracker.system.service.login;

import com.tracker.system.domain.dto.login.LoginDTO;
import com.tracker.system.service.user.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Resource
    private UserService userService;

    public void login(LoginDTO loginDTO) {
    }
}
