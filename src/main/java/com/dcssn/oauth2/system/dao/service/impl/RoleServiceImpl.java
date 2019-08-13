package com.dcssn.oauth2.system.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dcssn.oauth2.system.dao.entity.Menu;
import com.dcssn.oauth2.system.dao.entity.Role;
import com.dcssn.oauth2.system.dao.entity.RoleMenu;
import com.dcssn.oauth2.system.dao.mapper.RoleMapper;
import com.dcssn.oauth2.system.dao.service.MenuService;
import com.dcssn.oauth2.system.dao.service.RoleMenuService;
import com.dcssn.oauth2.system.dao.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lihy
 * @since 2019-08-13
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private MenuService menuService;

    @Override
    public Role findByIdWithMenus(Long id) {
        Role role = this.getById(id);
        addMenu(role);
        return role;
    }

    @Override
    public List<Role> findAllWithMenus() {
        List<Role> roles = this.list();
        for (Role role : roles) {
            addMenu(role);
        }
        return roles;
    }


    private void addMenu(Role role) {
        if (role != null) {
            List<RoleMenu> roleMenus = roleMenuService.findByRoleId(role.getId());
            if (!roleMenus.isEmpty()) {
                List<Menu> menus = new ArrayList<>(menuService.listByIds(roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList())));
                role.setMenus(menus);
            }
        }
    }
}
