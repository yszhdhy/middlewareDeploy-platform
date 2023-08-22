package com.example.middlewaredeploy.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultType {

    BASIC_ERROR(90005,"服务端未兼容异常"),

    NOT_SUPPORTED_CODE(90001,"不支持的消息CODE"),

    NOT_FIND_TARGET(90002,"消息发送失败，发送消息前请指定接收对象"),

    NOT_REPEAT_LOGIN(90003,"该用户已上线，请更换昵称后再换~"),

    MESSAGE_FORMAT_ERROR(90004,"发送消息格式错误，请确认后在试"),

    SERVICE_CONNECTED(80001,"服务连接建立成功"),

    GET_ONLINE_USERS(80002,"获取用户列表"),

    JOIN_GROUP_SUCCESS(80003,"加入系统默认群聊成功~"),

    CHAT_MESSAGE(80004,"聊天消息"),

    JOIN_USER(80005,"新用户上线")

    ;

    private Integer code;

    private String message;

}
