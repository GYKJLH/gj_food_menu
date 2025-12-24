package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dao.MenuMapper;
import org.example.entity.Response;
import org.example.entity.dto.MenuAddDTO;
import org.example.entity.dto.MenuEditDTO;
import org.example.entity.dto.MenuPageDTO;
import org.example.entity.entity.Menu;
import org.example.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Value("${file.path}")
    private String filePath;
    @Value("${file.external-url}")
    private String externalUrl;

    @Override
    public Response list(MenuPageDTO menuPageDTO) {
        List<Menu> all = this.list(new LambdaQueryWrapper<Menu>()
                .eq(isNotBlank(menuPageDTO.getName()), Menu::getName, menuPageDTO.getName())
                .like(isNotBlank(menuPageDTO.getIngredients()), Menu::getIngredients, menuPageDTO.getIngredients())
                .eq(isNotBlank(menuPageDTO.getCook()), Menu::getCook, menuPageDTO.getCook())
                .eq(isNotBlank(menuPageDTO.getType()), Menu::getType, menuPageDTO.getType())
        );
        int page = menuPageDTO.getPage() == null ? 1 : menuPageDTO.getPage();
        int size = menuPageDTO.getPageSize() == null ? 10 : menuPageDTO.getPageSize();
        int fromIndex = (page - 1) * size;
        int toIndex = Math.min(fromIndex + size, all.size());
        // 防止越界
        if (fromIndex >= all.size()) {
            all = new ArrayList<>();
        }
        Map<String, Object> result = new HashMap<>();
        result.put("total", all.size());
        result.put("rows", all.subList(fromIndex, toIndex));
        return new Response(200, "success", result);
    }

    @Override
    public Response add(MenuAddDTO menuAddDTO) {
        if (!this.list(new LambdaQueryWrapper<Menu>().eq(Menu::getName, menuAddDTO.getName())).isEmpty()) {
            return new Response(400, "已经有这个菜了！");
        }
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuAddDTO,menu);
        return new Response(200, "success", this.save(menu));
    }

    @Override
    public Response randomMenu() {
        List<Menu> menus = this.list(new LambdaQueryWrapper<Menu>());
        if (menus.isEmpty()) {
            return new Response(400,"没有相应菜单！");
        }
        return new Response(200, "success", menus.get(ThreadLocalRandom.current().nextInt(0, menus.size())));
    }

    @Override
    public Response edit(MenuEditDTO menuEditDTO) {
        if (!this.list(new LambdaQueryWrapper<Menu>().eq(Menu::getName, menuEditDTO.getName()).ne(Menu::getId, menuEditDTO.getId())).isEmpty()) {
            return new Response(400, "已经有这个菜了！");
        }
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuEditDTO,menu);
        return new Response(200, "success", this.updateById(menu));
    }

    @Override
    public Response upload(MultipartFile file) {
        if (!isImage(file)) {
            return new Response(400, "请上传正确的图片格式！");
        }
        try {
            // 确保目录存在
            File dir = new File(filePath);
            if (!dir.exists()) dir.mkdirs();

            // 构造唯一文件名
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename != null && originalFilename.contains(".")
                    ? originalFilename.substring(originalFilename.lastIndexOf("."))
                    : ".png";
            String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS")) + suffix;

            // 保存文件
            File dest = new File(dir, fileName);
            file.transferTo(dest);

            // 拼接访问 URL（与静态资源映射路径匹配）
            String fileUrl = externalUrl + "/file/" + fileName;

            return new Response(200, "success", fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return new Response(500, "上传失败：" + e.getMessage());
        }
    }

    private boolean isBlank(String str) {
        return str == null || str.equals("");
    }

    private boolean isNotBlank(String str) {
        return str != null && !str.equals("");
    }

    public boolean isImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return false;
        }
        String filename = file.getOriginalFilename();
        if (filename == null) return false;

        String lower = filename.toLowerCase();
        return lower.endsWith(".png") || lower.endsWith(".jpg") || lower.endsWith(".jpeg")
                || lower.endsWith(".gif") || lower.endsWith(".bmp");
    }


}
