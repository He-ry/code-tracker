package com.tracker.system.service.auth;

import cn.dev33.satoken.stp.StpUtil;
import com.tracker.framework.exception.ServiceException;
import com.tracker.system.domain.dto.auth.LoginDTO;
import com.tracker.system.domain.dto.auth.LoginUser;
import com.tracker.system.domain.dto.auth.RegisterDTO;
import com.tracker.system.domain.vm.auth.UserRegisterVm;
import com.tracker.system.models.entity.UserDO;
import com.tracker.system.models.mapper.MenuMapper;
import com.tracker.system.models.mapper.RoleMapper;
import com.tracker.system.models.mapper.UserMapper;
import com.tracker.system.utils.LoginUserUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Slf4j
@Service
@Validated
public class AuthServiceImpl implements AuthService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private MenuMapper menuMapper;

    @Override
    public String login(LoginDTO loginDTO) {
        UserDO userDO = userMapper.selectOne(UserDO::getUsername, loginDTO.getUsername().trim());
        if (userDO == null) {
            throw new ServiceException("账号不存在！");
        }
        if (userDO.getStatus() == 0) {
            throw new RuntimeException("账号已禁用");
        }
        if (!new BCryptPasswordEncoder().matches(loginDTO.getPassword(), userDO.getPassword().trim())) {
            throw new ServiceException("密码错误！");
        }
        StpUtil.login(userDO.getId());
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(userDO);
        loginUser.setRoles(roleMapper.selectRoleCodesByUserId(userDO.getId()));
        loginUser.setPermissions(menuMapper.selectPermissionsByUserId(userDO.getId()));
        LoginUserUtil.setCurrentUser(loginUser);
        userDO.setLoginDate(LocalDateTime.now());
        userMapper.updateById(userDO);
        return StpUtil.getTokenValue();
    }

    @Override
    public UserRegisterVm register(RegisterDTO registerDTO) {
        UserDO userDO = userMapper.selectFirstOne(UserDO::getUsername, registerDTO.getUsername());
        if (userDO != null) {
            throw new ServiceException("账号已存在！");
        }
        userDO = UserDO.builder()
                .username(registerDTO.getUsername())
                .nickname(registerDTO.getUsername())
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

    @Override
    public void logout() {
        //多端登录只会退出当前用户
        StpUtil.logout();
    }


}
