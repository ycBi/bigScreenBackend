package com.sf.bigscreen;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan(basePackages ="com.sf.bigscreen.mapper")
@SpringBootApplication
public class BigscreenApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigscreenApplication.class, args);
    }

}
