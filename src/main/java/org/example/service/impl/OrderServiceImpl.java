package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dao.MenuMapper;
import org.example.dao.OrderMapper;
import org.example.dao.UserMapper;
import org.example.entity.Response;
import org.example.entity.dto.OrderDTO;
import org.example.entity.dto.UserDTO;
import org.example.entity.entity.Menu;
import org.example.entity.entity.Order;
import org.example.entity.entity.User;
import org.example.entity.vo.MenuForOrderVO;
import org.example.service.OrderService;
import org.example.utils.JwtUtil;
import org.example.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Response register(UserDTO userDTO) {
        String password = userDTO.getPassword();
        String username = userDTO.getUsername();
        List<User> users = userMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (!users.isEmpty()) {
            return Response.fail("用户名已存在！");
        }
        String phone = userDTO.getPhone();
        String secretPassword = PasswordUtil.encrypt(password);
        User user = User.builder()
                .username(username)
                .password(secretPassword)
                .phone(phone)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        userMapper.insert(user);
        return Response.success("注册成功！");
    }

    @Override
    public Response login(UserDTO userDTO) {

        List<User> users = userMapper.selectList(
                new LambdaQueryWrapper<User>()
                        .and(w -> w
                                .eq(User::getUsername, userDTO.getUsername())
                                .or()
                                .eq(User::getPhone, userDTO.getUsername())
                        )
        );
        if (users.isEmpty()) {
            return Response.fail("用户不存在！");
        }
        if (users.size() > 1) {
            return Response.fail("数据错误，请联系管理员！");
        }
        if (!PasswordUtil.matches(userDTO.getPassword(), users.get(0).getPassword())) {
            return Response.fail("用户名或密码错误！");
        }
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", users.get(0).getUsername());
        userInfo.put("password", userDTO.getPassword());
        return Response.success(JwtUtil.generateToken(userInfo),"登录成功！");
    }


    @Override
    public Response<List<MenuForOrderVO>> listMenu(OrderDTO orderDTO) {
        menuMapper.selectList(new LambdaQueryWrapper<Menu>());
        List<MenuForOrderVO> menuForOrderVOS = new ArrayList<>();
        MenuForOrderVO menuForOrderVO = MenuForOrderVO.builder()
                .id(1)
                .menuId(1)
                .menuName("这是菜名")
                .image("http://127.0.0.1:10000/file/20251114_112853514.jpg")
                .introduction("这是第一道菜，看看是否正常显示")
                .build();
        menuForOrderVOS.add(menuForOrderVO);
        return Response.success(menuForOrderVOS);
    }
}
