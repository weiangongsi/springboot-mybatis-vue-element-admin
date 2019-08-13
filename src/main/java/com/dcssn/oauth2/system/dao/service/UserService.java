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

    /**
     * 校验用户名是否可用
     *
     * @param id       用户id
     * @param username 用户名
     * @return true 可用
     */
    boolean validUsername(Long id, String username);

    /**
     * 移除role
     *
     * @param id 用户id
     */
    void removeRole(Long id);
}
