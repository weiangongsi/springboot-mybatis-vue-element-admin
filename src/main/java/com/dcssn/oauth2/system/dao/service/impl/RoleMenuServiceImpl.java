package com.dcssn.oauth2.system.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dcssn.oauth2.system.dao.entity.RoleMenu;
import com.dcssn.oauth2.system.dao.mapper.RoleMenuMapper;
import com.dcssn.oauth2.system.dao.service.RoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lihy
 * @since 2019-08-13
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public List<RoleMenu> findByRoleId(Long roleId) {
        QueryWrapper<RoleMenu> qw = new QueryWrapper<>();
        qw.lambda().eq(RoleMenu::getRoleId, roleId);
        return this.list(qw);
    }
}
