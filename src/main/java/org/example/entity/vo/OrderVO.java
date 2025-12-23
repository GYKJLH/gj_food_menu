package org.example.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO {

    private Integer id;

    private String menuName;

    private String orderUser;

    private LocalDateTime orderTime;

    private Integer menuCount;
}
