package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.entity.entity.Order;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
