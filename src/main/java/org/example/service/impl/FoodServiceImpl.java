package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dao.FoodMapper;
import org.example.entity.Food;
import org.example.entity.FoodDTO;
import org.example.entity.Response;
import org.example.service.FoodService;
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
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements FoodService {

    @Value("${file.path}")
    private String filePath;
    @Value("${file.external-url}")
    private String externalUrl;

    @Override
    public Response list(FoodDTO foodDTO) {
        List<Food> all = this.list(new LambdaQueryWrapper<Food>()
                .eq(isNotBlank(foodDTO.getName()), Food::getName, foodDTO.getName())
                .like(isNotBlank(foodDTO.getIngredients()), Food::getIngredients, foodDTO.getIngredients())
                .eq(isNotBlank(foodDTO.getCook()), Food::getCook, foodDTO.getCook())
                .eq(foodDTO.getType() != null, Food::getType, foodDTO.getType())
        );
        int page = foodDTO.getPage() == null ? 1 : foodDTO.getPage();
        int size = foodDTO.getPageSize() == null ? 10 : foodDTO.getPageSize();
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
    public Response add(Food food) {
        if (!this.list(new LambdaQueryWrapper<Food>().eq(Food::getName, food.getName())).isEmpty()) {
            return new Response(400, "已经有这个菜了！");
        }
        return new Response(200, "success", this.save(food));
    }

    @Override
    public Response randomFood(Integer type) {
        List<Food> foods = this.list(new LambdaQueryWrapper<Food>().eq(Food::getType, type));
        if (foods.isEmpty()) {
            return new Response(400,"没有相应菜单！");
        }
        return new Response(200, "success", foods.get(ThreadLocalRandom.current().nextInt(0, foods.size())));
    }

    @Override
    public Response edit(Food food) {
        if (!this.list(new LambdaQueryWrapper<Food>().eq(Food::getName, food.getName()).ne(Food::getId, food.getId())).isEmpty()) {
            return new Response(400, "已经有这个菜了！");
        }
        return new Response(200, "success", this.updateById(food));
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

            // 拼接访问 URL（注意这里要与你的静态资源映射路径匹配）
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
