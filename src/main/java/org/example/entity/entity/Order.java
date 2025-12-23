package org.example.entity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("`order`")
public class Order {

    @TableId
    private Integer id;

    @TableField(value = "menu_id")
    private Integer menuId;

    @TableField(value = "menu_count")
    private Integer menuCount;

    @TableField(value = "order_user")
    private String orderUser;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(exist = false)
    private String menuName;

}
