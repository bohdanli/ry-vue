package com.ruoyi.framework.config;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariDataSource;

/**
 * HikariCP 配置多数据源
 *
 * @author ruoyi
 */
@Configuration
public class HikariConfig {

    @Bean(name = "masterDataSource")
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource masterDataSource() {
        return new HikariDataSource();
    }

}

