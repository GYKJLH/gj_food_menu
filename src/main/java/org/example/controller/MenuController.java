package org.example.controller;

import org.example.entity.entity.Menu;
import org.example.entity.dto.MenuDTO;
import org.example.service.MenuService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/menu")
@MapperScan(basePackages = {"org.example.dao"})
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public ResponseEntity<?> list(MenuDTO menuDTO) {
        return ResponseEntity.ok(menuService.list(menuDTO));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Menu menu) {
        return ResponseEntity.ok(menuService.add(menu));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody Menu menu) {
        return ResponseEntity.ok(menuService.edit(menu));
    }

    @GetMapping("/random")
    public ResponseEntity<?> random(@RequestParam Integer type) {
        return ResponseEntity.ok(menuService.randomMenu(type));
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(menuService.upload(file));
    }
}
