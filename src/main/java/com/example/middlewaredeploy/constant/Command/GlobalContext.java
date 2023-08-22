package com.example.middlewaredeploy.constant.Command;

import io.netty.channel.ChannelHandlerContext;

/**
 * @project middlewareDeploy
 * @description
 * @author capture or new
 * @date 2023/7/18 10:31:58
 * @version 1.0
 */
public class GlobalContext {
    private static ChannelHandlerContext ctx;

    public static synchronized void setContext(ChannelHandlerContext context) {
        ctx = context;
    }

    public static synchronized ChannelHandlerContext getContext() {
        return ctx;
    }
}
