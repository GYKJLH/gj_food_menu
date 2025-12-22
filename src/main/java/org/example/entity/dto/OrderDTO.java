package org.example.entity.dto;

import lombok.Data;

@Data
public class OrderDTO {

    // 菜名
    private String name;

    // 荤-1，素-2
    private Integer type;

}
