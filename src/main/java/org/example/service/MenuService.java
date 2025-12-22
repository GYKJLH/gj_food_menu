package org.example.service;

import org.example.entity.entity.Menu;
import org.example.entity.dto.MenuDTO;
import org.example.entity.Response;
import org.springframework.web.multipart.MultipartFile;

public interface MenuService {

    Response list(MenuDTO menuDTO);

    Response add(Menu menu);

    Response randomMenu(Integer type);

    Response edit(Menu menu);

    Response upload(MultipartFile file);
}
