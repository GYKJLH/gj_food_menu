package org.example.service;

import org.example.entity.Response;
import org.example.entity.dto.OrderAddDTO;
import org.example.entity.dto.OrderDTO;
import org.example.entity.dto.UserDTO;
import org.example.entity.vo.MenuForOrderVO;
import org.example.entity.vo.OrderVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderService {

    Response register(UserDTO userDTO);

    Response login(UserDTO userDTO);

    Response<List<MenuForOrderVO>> listMenu(OrderDTO orderDTO);

    Response add(List<OrderAddDTO> orderAddDTOs, HttpServletRequest request);

    Response<List<OrderVO>> list(HttpServletRequest request);
}
