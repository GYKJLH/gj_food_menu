package org.example.service;

import org.example.entity.Food;
import org.example.entity.FoodDTO;
import org.example.entity.User;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface UserService {

    Map<String, Object> login(User user);
}
