package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

// 启动类
@SpringBootApplication
@ServletComponentScan
public class RecipeManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeManagerApplication.class, args);
        System.out.println("食谱管理模块启动成功！");
    }
}
