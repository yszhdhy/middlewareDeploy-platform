package com.example.middlewaredeploy.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @project middlewareDeploy
 * @description
 * @author capture or new
 * @date 2023/7/1 13:40:41
 * @version 1.0
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class ServerDispositionVo {
    private String host;
    private Integer port;
    private String userName;
    private String password;
}
