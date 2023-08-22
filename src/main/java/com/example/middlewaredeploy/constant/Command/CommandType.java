package com.example.middlewaredeploy.constant.Command;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author beiming
 */
@Getter
@AllArgsConstructor
public enum CommandType {

    /**
     * 建立连接
     */
    CONNECTION(10001),

    ERROR(-1),
    ;

    private final Integer code;

    public static CommandType match(Integer code){
        for (CommandType value : CommandType.values()) {
            if (value.getCode().equals(code)){
                return value;
            }
        }
        return ERROR;
    }
}
