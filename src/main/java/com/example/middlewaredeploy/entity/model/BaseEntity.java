package com.example.middlewaredeploy.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @project middlewareDeploy
 * @description 基类
 * @author capture or new
 * @date 2023/7/1 09:51:31
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    private String userName;
    private String password;
    private String version;
    private boolean isSelect;
}
