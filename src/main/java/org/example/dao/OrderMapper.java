package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.entity.dto.OrderAddDTO;
import org.example.entity.entity.Order;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    void saveBatchs(@Param("userName") String userName, @Param("orderAddDTOs") List<OrderAddDTO> orderAddDTOs, @Param("now") LocalDateTime now);

    List<Order> listMyOrders(@Param("userName") String userName);
}
