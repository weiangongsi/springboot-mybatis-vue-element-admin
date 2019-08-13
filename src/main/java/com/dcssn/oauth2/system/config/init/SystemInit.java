package com.dcssn.oauth2.system.config.init;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 启动时，没有admin信息则系统初始化
 *
 * @author lihaoyang
 * @since 2019/8/6
 */
@Component
public class SystemInit implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
    }

}