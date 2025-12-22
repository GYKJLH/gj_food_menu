package org.example.service;

import org.example.entity.Menu;
import org.example.entity.MenuDTO;
import org.example.entity.Response;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface MenuService {

    Response list(MenuDTO menuDTO);

    Response add(Menu menu);

    Response randomMenu(Integer type);

    Response edit(Menu menu);

    Response upload(MultipartFile file);
}
