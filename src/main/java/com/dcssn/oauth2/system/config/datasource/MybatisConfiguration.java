package com.dcssn.oauth2.system.config.datasource;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lihaoyang
 * @since 2019/8/13
 */
@MapperScan("com.dcssn.oauth2.**.dao.mapper")
@Configuration
public class MybatisConfiguration {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor()
    {
        return new PaginationInterceptor();
    }
}
