package com.sunroom;

import com.zaxxer.hikari.HikariDataSource;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class ConfigDataSource {
    @Resource
    private StringEncryptor encryptor;
    @Resource
    private Environment environment;

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create(HikariDataSource.class.getClassLoader());
        String url = environment.getProperty("spring.datasource.url");
        String pwd = environment.getProperty("spring.datasource.password");
        String username = environment.getProperty("spring.datasource.username");
        String driver = environment.getProperty("spring.datasource.driver-class-name");
        builder.url(url);
        builder.driverClassName(driver);
        builder.username(encryptor.decrypt(username));
        builder.password(encryptor.decrypt(pwd));
        return builder.build();
    }
}
