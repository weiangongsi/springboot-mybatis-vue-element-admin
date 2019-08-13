package com.dcssn.oauth2.system.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dcssn.oauth2.system.dao.entity.User;
import com.dcssn.oauth2.system.dao.mapper.UserMapper;
import com.dcssn.oauth2.system.dao.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lihy
 * @since 2019-08-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User findByUsername(String username) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.lambda().eq(User::getUsername, username);
        return this.getOne(qw);
    }
}
