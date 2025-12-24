package org.example.service;

import org.example.entity.Response;
import org.example.entity.dto.MenuAddDTO;
import org.example.entity.dto.MenuEditDTO;
import org.example.entity.dto.MenuPageDTO;
import org.springframework.web.multipart.MultipartFile;

public interface MenuService {

    Response list(MenuPageDTO menuPageDTO);

    Response add(MenuAddDTO menuAddDTO);

    Response randomMenu();

    Response edit(MenuEditDTO menuEditDTO);

    Response upload(MultipartFile file);
}
