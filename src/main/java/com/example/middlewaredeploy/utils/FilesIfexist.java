package com.example.middlewaredeploy.utils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.IOException;
import java.io.InputStream;

/**
 * @project middlewareDeploy
 * @description 判断指定文件夹是否存在
 * @author capture or new
 * @date 2023/7/14 20:27:28
 * @version 1.0
 */
public class FilesIfexist {


    public static boolean IsNotExist(Session session,String folderPath){

        ChannelExec channel = null;
        try {
            // 检查文件夹是否存在
//            String folderPath = "/opt/dockerCompose"; // 要检查的文件夹路径
            String checkCommand = "test -d " + folderPath + " && echo exists || echo doesnotexist";
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(checkCommand);
            channel.setErrStream(System.err);
            channel.connect();
            InputStream inputStream = channel.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            StringBuilder output = new StringBuilder();
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.append(new String(buffer, 0, bytesRead));
            }
            channel.disconnect();

            // 解析输出并判断文件夹是否存在
            boolean folderExists = output.toString().trim().equals("exists");
            System.out.println("文件夹存在: " + folderExists);
            return folderExists;
        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
