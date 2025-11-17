package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("menu")
@Data
public class Food {

    @TableId(type = IdType.AUTO)
    private Integer id;

    // 菜名
    private String name;

    // 食材
    private String ingredients;

    //做法
    private String method;

    // 厨师
    private String cook;

    // 荤-1，素-2
    private Integer type;

    // 图片
    private String image;

}
