package com.attencecheckin.javabackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
/**mybatis*/
@MapperScan("com.attencecheckin.javabackend.dao")
@EnableTransactionManagement
public class ForumcrawlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumcrawlApplication.class, args);
    }

}
