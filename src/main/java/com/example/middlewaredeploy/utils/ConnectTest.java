package com.example.middlewaredeploy.utils;
import com.example.middlewaredeploy.exception.DeployException;
import com.jcraft.jsch.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.middlewaredeploy.constant.ServerDisposition.*;

/**
 * @project middlewareDeploy
 * @description 做服务器的连接测试 验证是否能成功连接服务器
 * @author capture or new
 * @date 2023/7/16 15:17:18
 * @version 1.0
 */
public class ConnectTest {

    public static String connect() {
        JSch jsch = new JSch();

        String username = serverDispositionVo.getUserName();
        String host = serverDispositionVo.getHost();
        int port = serverDispositionVo.getPort();

        Session session = null;

        try {
            // 创建 Session 对象
            session = jsch.getSession(username, host, port);

            // 设置密码身份验证方式
            session.setPassword(serverDispositionVo.getPassword());

            // 设置 StrictHostKeyChecking 为 no，避免 HostKey 校验导致连接失败
            session.setConfig("StrictHostKeyChecking", "no");

            // 设置连接超时时间
            session.setTimeout(10000);

            // 连接到远程服务器
            session.connect();

            // 连接成功
            return "远程服务器连接成功";
        } catch (JSchException e) {
            throw new DeployException(201,"远程服务器连接失败："+e.getMessage());
        } finally {
            // 断开连接
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
    }
}
