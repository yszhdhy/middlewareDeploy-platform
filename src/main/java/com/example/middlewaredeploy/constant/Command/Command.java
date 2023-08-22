package com.example.middlewaredeploy.constant.Command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author beiming
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Command {

    /**
     * 连接信息编码
     */
    private Integer code;

    /**
     * 昵称
     */
    private String nickname;

}
