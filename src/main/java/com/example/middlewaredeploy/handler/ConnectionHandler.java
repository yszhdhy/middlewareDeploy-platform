package com.example.middlewaredeploy.handler;

import com.alibaba.fastjson2.JSON;
import com.example.middlewaredeploy.constant.Command.Command;
import com.example.middlewaredeploy.nettyServer.MiddlewareServer;
import com.example.middlewaredeploy.result.Result;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.internal.StringUtil;

import java.time.LocalDateTime;

/**
 * @author beiming
 */
public class ConnectionHandler {


    public static void execute(ChannelHandlerContext ctx, Command command){
        System.out.println("保存通道成功");
        MiddlewareServer.channels.put("connection",ctx.channel());
        MiddlewareServer.channels.get("connection").writeAndFlush(new TextWebSocketFrame("保存成功了"));
    }

}
