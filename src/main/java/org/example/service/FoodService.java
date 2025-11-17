package org.example.service;

import org.example.entity.Food;
import org.example.entity.FoodDTO;
import org.example.entity.Response;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FoodService {

    Response list(FoodDTO foodDTO);

    Response add(Food food);

    Response randomFood(Integer type);

    Response edit(Food food);

    Response upload(MultipartFile file);
}
