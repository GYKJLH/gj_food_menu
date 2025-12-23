package org.example.utils;

import org.example.entity.entity.Menu;
import org.example.entity.vo.MenuForOrderVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderConvert {

    @Mapping(source = "name", target = "menuName")
    @Mapping(source = "id", target = "menuId")
    MenuForOrderVO toMenuForOrderVO(Menu menu);

    @Mapping(source = "name", target = "menuName")
    @Mapping(source = "id", target = "menuId")
    List<MenuForOrderVO> toMenuForOrderVOList(List<Menu> menus);
}
