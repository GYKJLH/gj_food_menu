package org.example.controller;

import org.example.entity.Food;
import org.example.entity.FoodDTO;
import org.example.service.FoodService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/food")
@MapperScan(basePackages = {"org.example.dao"})
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping("/list")
    public ResponseEntity<?> list(FoodDTO foodDTO) {
        return ResponseEntity.ok(foodService.list(foodDTO));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Food food) {
        return ResponseEntity.ok(foodService.add(food));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody Food food) {
        return ResponseEntity.ok(foodService.edit(food));
    }

    @GetMapping("/random")
    public ResponseEntity<?> random(@RequestParam Integer type) {
        return ResponseEntity.ok(foodService.randomFood(type));
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(foodService.upload(file));
    }
}
