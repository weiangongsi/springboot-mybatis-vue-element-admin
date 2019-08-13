package com.dcssn.oauth2.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dcssn.oauth2.system.dao.entity.Menu;
import com.dcssn.oauth2.system.dao.service.MenuService;
import com.dcssn.oauth2.system.utils.HttpResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单
 *
 * @author lihaoyang
 * @since 2019/7/29
 */
@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 所有role
     *
     * @return list
     */
    @GetMapping("")
    public HttpResultUtils.HttpResult all() {
        QueryWrapper<Menu> qw = new QueryWrapper<>();
        qw.lambda().eq(Menu::getLevel, 1);
        // 一级菜单
        return HttpResultUtils.success(menuService.list(qw));
    }


}
