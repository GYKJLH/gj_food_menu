package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Menu;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
}
