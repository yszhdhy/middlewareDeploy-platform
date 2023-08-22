package com.example.middlewaredeploy.exception;


import com.example.middlewaredeploy.result.ResultCodeEnum;
import lombok.Data;

@Data
public class DeployException extends RuntimeException {

    private Integer code;//状态码
    private String msg;//描述信息

    public DeployException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    /**
     * 接收枚举类型对象
     * @param resultCodeEnum
     */
    public DeployException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "ERPException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
