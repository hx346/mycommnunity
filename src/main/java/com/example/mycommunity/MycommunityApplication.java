package com.example.mycommunity;
//启动入口
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mycommunity.mapper")
public class MycommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MycommunityApplication.class, args);
    }

}
