package com.sunroom;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestJDBC {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/test/jdbc")
    public String onTestJDBC() {
        String sql = "select * from sys";
        jdbcTemplate.execute(sql);
        return "成功";
    }
}
