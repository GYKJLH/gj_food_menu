package org.example.utils;

import org.example.entity.entity.Order;
import org.example.entity.vo.OrderVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderConvert {


    @Mapping(source = "menuName", target = "menuName")
    @Mapping(source = "createTime", target = "orderTime")
    OrderVO toVO(Order order);

    @Mapping(source = "menuName", target = "menuName")
    @Mapping(source = "createTime", target = "createTime")
    List<OrderVO> toOrderVOList(List<Order> orders);
}
