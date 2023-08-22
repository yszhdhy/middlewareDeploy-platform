package com.example.middlewaredeploy.utils;

import com.example.middlewaredeploy.constant.Command.GlobalContext;
import com.example.middlewaredeploy.handler.WebSocketHandler;
import com.example.middlewaredeploy.nettyServer.MiddlewareServer;
import com.example.middlewaredeploy.result.Result;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @project middlewareDeploy
 * @description 部署中间件
 * @author capture or new
 * @date 2023/7/14 19:51:01
 * @version 1.0
 */
public class deployMiddleware {

    public static void deploy(Session session, String command) throws IOException {
        io.netty.channel.Channel channel1 = MiddlewareServer.channels.get("connection");
//        开始执行
        InputStream inputStream = null;
        InputStream errorStream = null;
        Channel channel = null;

        try {
            // 打开通道
            channel = session.openChannel("exec");

            // 设置命令
            ((ChannelExec) channel).setCommand(command);

            // 获取命令执行的输出流
            inputStream = channel.getInputStream();
//            BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));

            // 连接通道
            channel.connect();

// 获取错误输出流
            errorStream = ((ChannelExec) channel).getErrStream();
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));

// 读取命令执行的输出和错误输出
            byte[] buffer = new byte[1024];
            StringBuilder output = new StringBuilder();

            while (true) {
                while (inputStream.available() > 0) {
                    int bytesRead = inputStream.read(buffer, 0, 1024);
                    if (bytesRead < 0) {
                        break;
                    }
                    String line = new String(buffer, 0, bytesRead);
                    System.out.print(line); // 输出到控制台
//                    写入到 netty 管道中
                    channel1.writeAndFlush(new TextWebSocketFrame(line));

                    output.append(line); // 保存到变量中
                }


                // 逐行读取错误输出流
                String errorLine;
                while ((errorLine = errorReader.readLine()) != null) {
                    System.out.println(errorLine); // 输出到标准输出流
                    // 写入到 netty 管道中
                    channel1.writeAndFlush(new TextWebSocketFrame(errorLine));

                    output.append(errorLine); // 保存到变量中
                }

                if (channel.isClosed()) {
                    if (inputStream.available() > 0 || errorStream.available() > 0) {
                        continue;
                    }
                    break;
                }

            }

        } catch (JSchException | IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭输入流、错误输出流、通道和会话
            inputStream.close();
            errorStream.close();
            channel.disconnect();
//            session.disconnect();
        }
    }
}
