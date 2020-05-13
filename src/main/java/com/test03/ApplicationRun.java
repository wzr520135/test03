package com.test03;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther wise wu
 * @Date 2020/5/11 11:18
 * @Description
 */
@SpringBootApplication
@MapperScan("com.test03.mapper")
public class ApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun.class,args);


    }
}
