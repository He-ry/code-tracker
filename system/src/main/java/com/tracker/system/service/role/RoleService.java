package com.tracker.system.service.role;

import com.tracker.framework.domain.PageResult;
import com.tracker.system.domain.dto.role.RoleListDTO;
import com.tracker.system.domain.dto.role.RoleSaveDTO;
import com.tracker.system.models.entity.RoleDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 角色 Service 接口
 *
 * @author admin
 */
public interface RoleService {

    /**
     * 创建角色
     *
     * @param roleSaveDTO 创建信息
     * @return 编号
     */
    Long createRole(@Valid RoleSaveDTO roleSaveDTO);

    /**
     * 更新角色
     *
     * @param roleSaveDTO 更新信息
     */
    void updateRole(@Valid RoleSaveDTO roleSaveDTO);

    /**
     * 删除角色
     *
     * @param id 编号
     */
    void deleteRole(Long id);

    /**
     * 批量删除角色
     *
     * @param ids 编号集合
     */
    void deleteRoleListByIds(List<Long> ids);

    /**
     * 获得角色
     *
     * @param id 编号
     * @return 角色
     */
    RoleDO getRole(Long id);

    /**
     * 获得角色分页
     *
     * @param roleListDTO 查询条件
     * @return 角色分页结果
     */
    PageResult<RoleDO> getRolePage(RoleListDTO roleListDTO);
}
