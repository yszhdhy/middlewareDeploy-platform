package com.example.middlewaredeploy.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @project middlewareDeploy
 * @description 分类类
 * @author capture or new
 * @date 2023/7/17 09:56:36
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDto {
    private Integer id;
    private String timestamp;
    private String title;
    private String description;
}
