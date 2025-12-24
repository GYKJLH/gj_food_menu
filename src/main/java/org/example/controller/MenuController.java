package org.example.controller;

import org.example.entity.dto.MenuAddDTO;
import org.example.entity.dto.MenuEditDTO;
import org.example.entity.dto.MenuPageDTO;
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
    public ResponseEntity<?> list(MenuPageDTO menuPageDTO) {
        return ResponseEntity.ok(menuService.list(menuPageDTO));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody MenuAddDTO menuAddDTO) {
        return ResponseEntity.ok(menuService.add(menuAddDTO));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody MenuEditDTO menuEditDTO) {
        return ResponseEntity.ok(menuService.edit(menuEditDTO));
    }

    @GetMapping("/random")
    public ResponseEntity<?> random() {
        return ResponseEntity.ok(menuService.randomMenu());
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(menuService.upload(file));
    }
}
