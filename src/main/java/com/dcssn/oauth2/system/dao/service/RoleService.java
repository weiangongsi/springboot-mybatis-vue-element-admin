package com.dcssn.oauth2.system.dao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dcssn.oauth2.system.dao.entity.Role;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lihy
 * @since 2019-08-13
 */
public interface RoleService extends IService<Role> {

    /**
     * 主键查询，包含菜单权限
     *
     * @param id 主键
     * @return role
     */
    Role findByIdWithMenus(Long id);

    /**
     * 所有role，包含菜单
     *
     * @return list
     */
    List<Role> findAllWithMenus();
}
