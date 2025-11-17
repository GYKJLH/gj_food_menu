package org.example.service.impl;

import org.example.entity.User;
import org.example.service.UserService;
import org.example.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserServiceImpl implements UserService {


    @Override
    public Map<String, Object> login(User user) {
        List<String> nameList = new ArrayList<String>();
        nameList.add("大郭");
        nameList.add("小蒋");
        String name = user.getUsername();
        String password = user.getPassword();
        if(isBlank(name) || isBlank(password)){
            throw new RuntimeException("账号或密码错误！");
        }
        if (!nameList.contains(name)) {
            throw new RuntimeException("账号或密码错误！");
        }
        if (name.equals("大郭") && !password.equals("jianglinhong0215")){
            throw new RuntimeException("账号或密码错误！");
        }else if (name.equals("小蒋") && !password.equals("guoyoukun0301")){
            throw new RuntimeException("账号或密码错误！");
        }
        Map<String,Object> userInfo = new HashMap<>();
        userInfo.put("username",name);
        userInfo.put("password",password);
        String token = JwtUtil.generateToken(userInfo);
        Map<String,Object> map = new HashMap<>();
        map.put("status",200);
        map.put("token",token);
        return map;
    }

    private boolean isBlank(String str) {
        return str == null || str.equals("");
    }
}
