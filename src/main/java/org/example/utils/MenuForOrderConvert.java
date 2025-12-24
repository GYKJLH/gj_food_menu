package org.example.utils;

import org.example.entity.entity.Menu;
import org.example.entity.vo.MenuForOrderVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuForOrderConvert {

    @Mapping(source = "name", target = "menuName")
    @Mapping(source = "id", target = "menuId")
    @Mapping(source = "type", target = "menuType")
    MenuForOrderVO toMenuForOrderVO(Menu menu);

    @Mapping(source = "name", target = "menuName")
    @Mapping(source = "id", target = "menuId")
    @Mapping(source = "type", target = "menuType")
    List<MenuForOrderVO> toMenuForOrderVOList(List<Menu> menus);

}
