package com.dcssn.oauth2.system.dao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dcssn.oauth2.system.dao.entity.RoleMenu;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lihy
 * @since 2019-08-13
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * 根据roleId查询
     *
     * @param roleId 角色id
     * @return roleMenu
     */
    List<RoleMenu> findByRoleId(Long roleId);
}
