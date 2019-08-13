package com.dcssn.oauth2.system.config.serurity;

import com.dcssn.oauth2.system.dao.entity.Role;
import com.dcssn.oauth2.system.dao.entity.User;
import com.dcssn.oauth2.system.dao.service.MenuService;
import com.dcssn.oauth2.system.dao.service.RoleService;
import com.dcssn.oauth2.system.dao.service.UserService;
import com.dcssn.oauth2.system.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 登陆认证
 *
 * @author lihy
 */
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    private final String ADMIN = "admin";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("Username not found!");
            }
            // 把之前登陆的用户清除
            SecurityUtil.logout(user.getUsername());
            Role role = null;
            if (ADMIN.equals(username)) {
                role = new Role();
                role.setMenus(menuService.list());
            } else {
                if (user.getRoleId() != null) {
                    role = roleService.findByIdWithMenus(user.getRoleId());
                }
            }
            user.setRole(role);
            return new CustomUserDetails(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("Username not found!");
        }
    }
}
