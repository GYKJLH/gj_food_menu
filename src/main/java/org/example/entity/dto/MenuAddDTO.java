package org.example.entity.dto;

import lombok.Data;

@Data
public class MenuAddDTO {

    // 菜名
    private String name;

    // 简介
    private String introduction;

    // 食材
    private String ingredients;

    //做法
    private String method;

    // 厨师
    private String cook;

    // 分类
    private String type;

    // 图片
    private String image;

}
