package com.tracker.system.service.auth;

import cn.dev33.satoken.stp.StpUtil;
import com.tracker.framework.exception.ServiceException;
import com.tracker.system.domain.dto.auth.LoginDTO;
import com.tracker.system.domain.dto.auth.RegisterDTO;
import com.tracker.system.domain.vm.auth.UserRegisterVm;
import com.tracker.system.models.entity.UserDO;
import com.tracker.system.models.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class AuthServiceImpl implements AuthService {

    @Resource
    private UserMapper userMapper;
    
    @Override
    public void login(LoginDTO loginDTO) {

    }

    @Override
    public UserRegisterVm register(RegisterDTO registerDTO) {
        UserDO userDO = userMapper.selectFirstOne(UserDO::getUsername, registerDTO.getUsername());
        if (userDO != null) {
            throw new ServiceException("账号已存在！");
        }
        userDO = UserDO.builder()
                .username(registerDTO.getUsername())
                .nickname(registerDTO.getNickname())
                .password(new BCryptPasswordEncoder().encode(registerDTO.getPassword()))
                .status(1)
                .build();
        userMapper.insert(userDO);
        String token = StpUtil.createLoginSession(userDO.getId());
        return UserRegisterVm.builder()
                .userId(userDO.getId())
                .accessToken(token)
                .build();
    }


}
