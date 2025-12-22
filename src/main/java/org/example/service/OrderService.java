package org.example.service;

import org.example.entity.Response;
import org.example.entity.dto.OrderDTO;
import org.example.entity.dto.UserDTO;
import org.example.entity.vo.MenuForOrderVO;

import java.util.List;

public interface OrderService {

    Response register(UserDTO userDTO);

    Response login(UserDTO userDTO);

    Response<List<MenuForOrderVO>> listMenu(OrderDTO orderDTO);

}
