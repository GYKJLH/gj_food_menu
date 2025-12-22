package org.example.entity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
@TableName("user")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "user_name")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

}
