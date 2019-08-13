package com.dcssn.oauth2.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dcssn.oauth2.common.constants.Security;
import com.dcssn.oauth2.system.dao.entity.Role;
import com.dcssn.oauth2.system.dao.entity.RoleMenu;
import com.dcssn.oauth2.system.dao.service.RoleMenuService;
import com.dcssn.oauth2.system.dao.service.RoleService;
import com.dcssn.oauth2.system.utils.HttpResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 角色
 *
 * @author lihaoyang
 * @since 2019/7/29
 */
@Secured(Security.PERMISSION)
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 所有role
     *
     * @return list
     */
    @GetMapping("")
    public HttpResultUtils.HttpResult all() {
        return HttpResultUtils.success(roleService.findAllWithMenus());
    }

    /**
     * 新增
     *
     * @return 是否成功
     */
    @PostMapping("")
    public HttpResultUtils.HttpResult create(@Valid @RequestBody Role role) {
        roleService.save(role);
        role.getMenus().forEach(menu -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(menu.getId());
            roleMenu.setRoleId(role.getId());
            roleMenuService.save(roleMenu);
        });
        return HttpResultUtils.success();
    }

    /**
     * 删除
     *
     * @return 是否成功
     */
    @DeleteMapping("/{id}")
    public HttpResultUtils.HttpResult delete(@PathVariable Long id) {
        // 移除先前的role-menu关联
        QueryWrapper<RoleMenu> delQw = new QueryWrapper<>();
        delQw.lambda().eq(RoleMenu::getRoleId, id);
        roleMenuService.remove(delQw);
        roleService.removeById(id);
        return HttpResultUtils.success();
    }

    /**
     * 修改
     *
     * @return 是否成功
     */
    @PutMapping("")
    public HttpResultUtils.HttpResult update(@Valid @RequestBody Role role) {
        Role currentRole = roleService.getById(role.getId());
        if (currentRole != null) {
            currentRole.setName(role.getName());
            currentRole.setCode(role.getCode());
            currentRole.setDescription(role.getDescription());
            // 移除先前的role-menu关联
            QueryWrapper<RoleMenu> delQw = new QueryWrapper<>();
            delQw.lambda().eq(RoleMenu::getRoleId, currentRole.getId());
            roleMenuService.remove(delQw);
            role.getMenus().forEach(menu -> {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(menu.getId());
                roleMenu.setRoleId(currentRole.getId());
                roleMenuService.save(roleMenu);
            });
            return HttpResultUtils.success();
        }
        return HttpResultUtils.fail();
    }

}
