package com.example.middlewaredeploy.utils;

import com.example.middlewaredeploy.constant.Command.GlobalContext;
import com.example.middlewaredeploy.nettyServer.MiddlewareServer;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CentosOperate {


    /**
     * @author CaptureOrNew
     * @description  执行命令
     * @date 15:02:42 2023/7/16
     * @param session
     * @param command
     * @return java.lang.String
     **/
    public static String executeCommand(Session session, String command) throws Exception {
        io.netty.channel.Channel channel1 = MiddlewareServer.channels.get("connection");

        ChannelHandlerContext ctx = GlobalContext.getContext();
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand(command);
        channel.setErrStream(System.err); // 转入错误输出流
        InputStream inputStream = channel.getInputStream();
        channel.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            // 写入到 netty 管道中
            channel1.writeAndFlush(new TextWebSocketFrame(line));
            output.append(line).append(System.lineSeparator());
            System.out.flush();
        }
        channel.disconnect();
        return output.toString();
    }


    public static boolean isCommandAvailable(Session session, String command) throws Exception {
        // 创建执行通道
        ChannelExec channel = (ChannelExec) session.openChannel("exec");

        // 设置执行的命令
        channel.setCommand("command -v " + command + " >/dev/null 2>&1 && echo \"Found\" || echo \"Not Found\"");

        // 获取命令执行的输出流
        InputStream inputStream = channel.getInputStream();

        // 连接通道
        channel.connect();

        // 读取命令执行的输出
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();

        // 关闭通道
        channel.disconnect();

        // 判断命令是否可用
        return line.equals("Found");
    }


}
