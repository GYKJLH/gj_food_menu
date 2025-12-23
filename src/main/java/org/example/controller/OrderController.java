package org.example.controller;

import org.example.entity.Response;
import org.example.entity.dto.OrderAddDTO;
import org.example.entity.dto.OrderDTO;
import org.example.entity.dto.UserDTO;
import org.example.entity.vo.MenuForOrderVO;
import org.example.service.OrderService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@MapperScan(basePackages = {"org.example.dao"})
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/noToken/register")
    public ResponseEntity<Response> register(@Validated @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(orderService.register(userDTO));
    }

    @PostMapping("/noToken/login")
    public ResponseEntity<Response> login(@Validated @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(orderService.login(userDTO));
    }

    @GetMapping("/listMenu")
    public ResponseEntity<Response<List<MenuForOrderVO>>> list(OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.listMenu(orderDTO));
    }

    @GetMapping("/add")
    public ResponseEntity<Response> add(OrderAddDTO orderAddDTO) {
        return ResponseEntity.ok(orderService.add(orderAddDTO));
    }

}
