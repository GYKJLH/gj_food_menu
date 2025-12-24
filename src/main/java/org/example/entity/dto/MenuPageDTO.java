package org.example.entity.dto;

import lombok.Data;

@Data
public class MenuPageDTO {

    // 菜名
    private String name;

    // 食材
    private String ingredients;

    // 厨师
    private String cook;

    // 分类
    private String type;

    private Integer page;

    private Integer pageSize;
}
