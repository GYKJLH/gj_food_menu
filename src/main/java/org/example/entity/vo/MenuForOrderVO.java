package org.example.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuForOrderVO {

    private Integer id;

    private Integer menuId;

    private String menuName;

    private String image;

    private String introduction;

}
