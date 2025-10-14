package com.tracker.system.service.user;

import cn.hutool.core.bean.BeanUtil;
import com.tracker.framework.domain.PageResult;
import com.tracker.framework.exception.ServiceException;
import com.tracker.system.domain.dto.user.UserListDTO;
import com.tracker.system.domain.dto.user.UserSaveDTO;
import com.tracker.system.models.entity.UserDO;
import com.tracker.system.models.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * 用户 Service 实现类
 *
 * @author admin
 */
@Service
@Validated
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Long createUser(UserSaveDTO userSaveDTO) {
        UserDO user = BeanUtil.toBean(userSaveDTO, UserDO.class);
        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public void updateUser(UserSaveDTO userSaveDTO) {
        validateUserExists(userSaveDTO.getId());
        UserDO updateObj = BeanUtil.toBean(userSaveDTO, UserDO.class);
        userMapper.updateById(updateObj);
    }

    @Override
    public void deleteUser(Long id) {
        validateUserExists(id);
        userMapper.deleteById(id);
    }

    @Override
    public void deleteUserListByIds(List<Long> ids) {
        userMapper.deleteByIds(ids);
    }


    private void validateUserExists(Long id) {
        if (userMapper.selectById(id) == null) {
            throw new ServiceException("用户不存在");
        }
    }

    @Override
    public UserDO getUser(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public PageResult<UserDO> getUserPage(UserListDTO userListDTO) {
        return userMapper.selectPage(userListDTO);
    }

}