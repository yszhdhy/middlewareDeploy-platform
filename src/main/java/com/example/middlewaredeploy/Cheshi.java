package com.example.middlewaredeploy;

import com.example.middlewaredeploy.utils.CentosOperate;
import com.example.middlewaredeploy.utils.FilesIfexist;
import com.jcraft.jsch.*;

import java.io.File;

import static com.example.middlewaredeploy.utils.UploadFileUtils.uploadFolder;

public class Cheshi {
    public static void main(String[] args) {
        JSch jsch = new JSch();
        Session session = null;
        ChannelSftp channel = null;

        try {
            String username = "root";
            String host = "10.14.0.84";
            int port = 22;
            String password = "123456";
            String localFolderPath = "Dockerfile";
            String remoteFolderPath = "/opt/ceshi";

            session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();


            // 递归上传整个文件夹
            uploadFolder(session, channel, localFolderPath, remoteFolderPath);

            System.out.println("文件夹上传成功！");
        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }



}
