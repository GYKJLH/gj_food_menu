package org.example.service;

import org.example.entity.entity.User;

import java.util.Map;

public interface UserService {

    Map<String, Object> login(User user);
}
