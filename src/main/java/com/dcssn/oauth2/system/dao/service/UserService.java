package com.dcssn.oauth2.system.dao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dcssn.oauth2.system.dao.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lihy
 * @since 2019-08-13
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名查找
     *
     * @param username 用户名
     * @return user
     */
    User findByUsername(String username);
}
