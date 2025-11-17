package org.example.controller;

import org.example.entity.User;
import org.example.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/noToken")
@MapperScan(basePackages = {"org.example.dao"})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        return userService.login(user);
    }
}
