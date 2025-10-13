package com.tracker.system.service.user;

import java.util.*;

import com.tracker.framework.domain.Result;
import com.tracker.system.domain.dto.user.UserListDTO;
import com.tracker.system.domain.dto.user.UserSaveDTO;
import com.tracker.system.models.entity.UserDO;
import jakarta.validation.*;

/**
 * 用户 Service 接口
 *
 * @author admin
 */
public interface UserService {

    /**
     * 创建用户
     *
     * @param userSaveDTO 创建信息
     * @return 编号
     */
    Long createUser(@Valid UserSaveDTO userSaveDTO);

    /**
     * 更新用户
     *
     * @param userSaveDTO 更新信息
     */
    void updateUser(@Valid UserSaveDTO userSaveDTO);

    /**
     * 删除用户
     *
     * @param id 编号
     */
    void deleteUser(Long id);

    /**
    * 批量删除用户
    *
    * @param ids 编号
    */
    void deleteUserListByIds(List<Long> ids);

    /**
     * 获得用户
     *
     * @param id 编号
     * @return 用户
     */
    UserDO getUser(Long id);

    /**
     * 获得用户分页
     *
     * @param userListDTO 分页查询
     * @return 用户分页
     */
    <T> Result<List<T>> getUserPage(UserListDTO userListDTO);

}