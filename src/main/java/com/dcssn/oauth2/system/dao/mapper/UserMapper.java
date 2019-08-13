package com.dcssn.oauth2.system.dao.mapper;

import com.dcssn.oauth2.system.dao.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lihy
 * @since 2019-08-13
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 移除role
     * @param id 用户id
     */
    @Update({"update user set role_id=null where id=#{id}"})
    void removeRole(Long id);
}
