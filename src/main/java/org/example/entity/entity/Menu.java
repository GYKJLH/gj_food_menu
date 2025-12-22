package org.example.entity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("menu")
@Data
public class Menu {

    @TableId(type = IdType.AUTO)
    private Integer id;

    // 菜名
    @TableField(value = "name")
    private String name;

    // 食材
    @TableField(value = "ingredients")
    private String ingredients;

    //做法
    @TableField(value = "method")
    private String method;

    // 厨师
    @TableField(value = "cook")
    private String cook;

    // 荤-1，素-2
    @TableField(value = "type")
    private Integer type;

    // 图片
    @TableField(value = "image")
    private String image;

    // 简介
    @TableField(value = "introduction")
    private String introduction;

}
