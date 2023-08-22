package com.example.middlewaredeploy.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @project middlewareDeploy
 * @description
 * @author capture or new
 * @date 2023/7/17 11:45:33
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MiddlewareDto {
    private Integer id;
    private Integer parentId;
    private String name;
    private String version;
    private String userName;
    private String password;
    private String description;
    private String imgUrl;
    private Boolean isSelect;

    // jar 包路径
    private String jarPath;

    //容器暴露端口
    private String expose;

    //作者
    private String author;

    // mysqlServiceHost
    private String mysqlServiceHost;
    private String nacosDatabase;
}
