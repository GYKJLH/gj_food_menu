package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.entity.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
