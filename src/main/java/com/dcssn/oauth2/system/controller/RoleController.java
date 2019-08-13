package com.dcssn.oauth2.system.controller;

import com.dcssn.oauth2.common.constants.Security;
import com.dcssn.oauth2.system.dao.entity.Role;
import com.dcssn.oauth2.system.utils.HttpResultUtils;
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


    /**
     * 所有role
     *
     * @return list
     */
    @GetMapping("")
    public HttpResultUtils.HttpResult all() {

        return HttpResultUtils.success();
    }

    /**
     * 新增
     *
     * @return 是否成功
     */
    @PostMapping("")
    public HttpResultUtils.HttpResult create(@Valid @RequestBody Role role) {

        return HttpResultUtils.success();
    }

    /**
     * 删除
     *
     * @return 是否成功
     */
    @DeleteMapping("/{id}")
    public HttpResultUtils.HttpResult delete(@PathVariable Long id) {

        return HttpResultUtils.success();
    }

    /**
     * 修改
     *
     * @return 是否成功
     */
    @PutMapping("")
    public HttpResultUtils.HttpResult update(@Valid @RequestBody Role role) {
//        Optional<Role> roleOptional = roleRepository.findById(role.getId());
//        if (roleOptional.isPresent()) {
//            Role currentRule = roleOptional.get();
//            currentRule.setName(role.getName());
//            currentRule.setCode(role.getCode());
//            currentRule.setDescription(role.getDescription());
//            currentRule.setMenus(role.getMenus());
//            roleRepository.save(currentRule);
//            return HttpResultUtils.success();
//        }
        return HttpResultUtils.fail();
    }

}
