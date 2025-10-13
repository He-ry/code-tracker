package com.tracker.system.service.user;

import cn.hutool.core.bean.BeanUtil;
import com.tracker.system.domain.dto.user.UserSaveDTO;
import com.tracker.system.models.entity.UserDO;
import com.tracker.system.models.mapper.UserMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import java.util.*;

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
        // 插入
        UserDO user = BeanUtil.toBean(userSaveDTO, UserDO.class);
        userMapper.insert(user);
        // 返回
        return user.get();
    }

    @Override
    public void updateUser(UserSaveReqVO updateReqVO) {
        // 校验存在
        validateUserExists(updateReqVO.getId());
        // 更新
        UserDO updateObj = BeanUtils.toBean(updateReqVO, UserDO.class);
        userMapper.updateById(updateObj);
    }

    @Override
    public void deleteUser(Long id) {
        // 校验存在
        validateUserExists(id);
        // 删除
        userMapper.deleteById(id);
    }

    @Override
    public void deleteUserListByIds(List<Long> ids) {
    // 删除
    userMapper.deleteByIds(ids);
    }


    private void validateUserExists(Long id) {
        if (userMapper.selectById(id) == null) {
            throw exception(USER_NOT_EXISTS);
        }
    }

    @Override
    public UserDO getUser(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public PageResult<UserDO> getUserPage(UserPageReqVO pageReqVO) {
        return userMapper.selectPage(pageReqVO);
    }

}