package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dao.MenuMapper;
import org.example.entity.Menu;
import org.example.entity.MenuDTO;
import org.example.entity.Response;
import org.example.service.MenuService;
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
    public Response list(MenuDTO menuDTO) {
        List<Menu> all = this.list(new LambdaQueryWrapper<Menu>()
                .eq(isNotBlank(menuDTO.getName()), Menu::getName, menuDTO.getName())
                .like(isNotBlank(menuDTO.getIngredients()), Menu::getIngredients, menuDTO.getIngredients())
                .eq(isNotBlank(menuDTO.getCook()), Menu::getCook, menuDTO.getCook())
                .eq(menuDTO.getType() != null, Menu::getType, menuDTO.getType())
        );
        int page = menuDTO.getPage() == null ? 1 : menuDTO.getPage();
        int size = menuDTO.getPageSize() == null ? 10 : menuDTO.getPageSize();
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
    public Response add(Menu menu) {
        if (!this.list(new LambdaQueryWrapper<Menu>().eq(Menu::getName, menu.getName())).isEmpty()) {
            return new Response(400, "已经有这个菜了！");
        }
        return new Response(200, "success", this.save(menu));
    }

    @Override
    public Response randomMenu(Integer type) {
        List<Menu> menus = this.list(new LambdaQueryWrapper<Menu>().eq(Menu::getType, type));
        if (menus.isEmpty()) {
            return new Response(400,"没有相应菜单！");
        }
        return new Response(200, "success", menus.get(ThreadLocalRandom.current().nextInt(0, menus.size())));
    }

    @Override
    public Response edit(Menu menu) {
        if (!this.list(new LambdaQueryWrapper<Menu>().eq(Menu::getName, menu.getName()).ne(Menu::getId, menu.getId())).isEmpty()) {
            return new Response(400, "已经有这个菜了！");
        }
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
