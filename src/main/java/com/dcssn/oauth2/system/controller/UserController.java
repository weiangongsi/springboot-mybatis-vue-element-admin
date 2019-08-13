package com.dcssn.oauth2.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dcssn.oauth2.common.BaseController;
import com.dcssn.oauth2.common.constants.Security;
import com.dcssn.oauth2.system.config.serurity.CustomUserDetails;
import com.dcssn.oauth2.system.dao.entity.User;
import com.dcssn.oauth2.system.dao.service.UserService;
import com.dcssn.oauth2.system.utils.HttpResultUtils;
import com.dcssn.oauth2.system.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Objects;

/**
 * 系统用户
 *
 * @author lihaoyang
 * @since 2019/7/22
 */
@Secured(Security.USER)
@RestController
@RequestMapping("user")
public class UserController extends BaseController {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     *
     * @return user
     */
    @GetMapping("info")
    public HttpResultUtils.HttpResult info() {
        CustomUserDetails userDetails = SecurityUtil.currentUser();
        if (userDetails == null) {
            return HttpResultUtils.fail();
        }
        // 设置密码为null
        User user = userDetails.getUser();
        user.setPassword("");
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        HttpResultUtils.HttpResult result = new HttpResultUtils.HttpResult(true, user);
        result.put("roles", authorities);
        return result;
    }

    @GetMapping("")
    public HttpResultUtils.HttpResult users() {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.lambda().ne(User::getUsername, "admin");
        IPage<User> page = userService.page(startPage(User.class), qw);
        return HttpResultUtils.success(page);
    }

    @PostMapping("")
    public HttpResultUtils.HttpResult create(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return HttpResultUtils.success();
    }

    @PutMapping("")
    public HttpResultUtils.HttpResult update(@RequestBody User user) {
        User currentUser = userService.getById(user.getId());
        if (currentUser != null) {
            currentUser.setNickname(user.getNickname());
            currentUser.setDescription(user.getDescription());
            if (user.getRole() != null && user.getRole().getId() != null) {

            }
            userService.save(currentUser);
            return HttpResultUtils.success();
        }
        return HttpResultUtils.fail();
    }

    @DeleteMapping("/{id}")
    public HttpResultUtils.HttpResult update(@PathVariable Long id) {
//        userRepository.deleteById(id);
        return HttpResultUtils.success();
    }


    @PostMapping("valid-username")
    public HttpResultUtils.HttpResult validUsername(Long id, String username) {
        User user = null;
        // 用户不存在返回成功
        if (user == null) {
            return HttpResultUtils.success(true);
        } else {
            if (user.getId().equals(id)) {
                return HttpResultUtils.success(true);
            } else {
                return HttpResultUtils.success(false);
            }
        }
    }

    /**
     * 退出
     *
     * @return 是否成功
     */
    @RequestMapping("logout")
    public HttpResultUtils.HttpResult logout() {
        try {
            User user = Objects.requireNonNull(SecurityUtil.currentUser()).getUser();
            SecurityUtil.logout(user.getUsername());
            return HttpResultUtils.success("退出成功");
        } catch (Exception e) {
            return HttpResultUtils.fail("获取授权信息失败");
        }
    }

}
