package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 关闭 csrf
                .csrf().disable()

                // 所有请求放行（不做 Web 访问控制）
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()

                // 关闭默认登录方式
                .formLogin().disable()
                .httpBasic().disable();
    }
}



