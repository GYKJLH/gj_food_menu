package org.example.entity.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuForOrderVO {

    private Integer id;

    private Integer menuId;

    private String menuName;

    private String image;

    private String introduction;

}
