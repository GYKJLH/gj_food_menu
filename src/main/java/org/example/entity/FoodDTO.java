package org.example.entity;

import lombok.Data;

@Data
public class FoodDTO {

    // 菜名
    private String name;

    // 食材
    private String ingredients;

    // 厨师
    private String cook;

    // 荤-1，素-2
    private Integer type;

    private Integer page;

    private Integer pageSize;
}
