package com.example.middlewaredeploy.handler;

import com.alibaba.fastjson2.JSON;
import com.example.middlewaredeploy.constant.Command.Command;
import com.example.middlewaredeploy.constant.Command.CommandType;
import com.example.middlewaredeploy.constant.Command.GlobalContext;
import com.example.middlewaredeploy.nettyServer.MiddlewareServer;
import com.example.middlewaredeploy.result.Result;
import com.example.middlewaredeploy.result.ResultType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

//    public static  ChannelHandlerContext ctxP = null;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame) throws Exception {

        try{
            System.out.println(frame.text());
            Command command = JSON.parseObject(frame.text(), Command.class);
            switch (CommandType.match(command.getCode())){
                case CONNECTION ->  ConnectionHandler.execute(ctx,command);
                default -> ctx.channel().writeAndFlush(Result.fail(ResultType.NOT_SUPPORTED_CODE.getMessage()));
            }

        }catch (Exception e){
//            System.out.println(e.getMessage());
            ctx.channel().writeAndFlush(Result.fail(ResultType.BASIC_ERROR.getMessage()));
        }

    }
}
