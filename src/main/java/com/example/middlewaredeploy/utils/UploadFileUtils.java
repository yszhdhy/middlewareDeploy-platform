package com.example.middlewaredeploy.utils;

import com.example.middlewaredeploy.constant.ServerDisposition;
import com.example.middlewaredeploy.entity.vo.ServerDispositionVo;
import com.example.middlewaredeploy.exception.DeployException;
import com.jcraft.jsch.*;

import java.io.File;

import static com.example.middlewaredeploy.constant.docker.DockerFilePath.DOCKER_COMPOSE_PATH;
import static com.example.middlewaredeploy.utils.CentosOperate.executeCommand;
import static com.example.middlewaredeploy.utils.CentosOperate.isCommandAvailable;

/**
 * @project middlewareDeploy
 * @description 上传文件
 * @author capture or new
 * @date 2023/7/1 13:44:38
 * @version 1.0
 */
public class UploadFileUtils {

//    public static boolean uploadFile(String localFilePath,String remoteFilePath) throws JSchException, SftpException {
//        System.out.println(ServerDisposition.serverDispositionVo);
//        ServerDispositionVo serverDispositionVo = ServerDisposition.serverDispositionVo;
//
//        boolean uploadSuccess = upload(localFilePath, remoteFilePath, serverDispositionVo.getHost(), serverDispositionVo.getPort(),
//                serverDispositionVo.getUserName(), serverDispositionVo.getPassword());
//
//
//        if (uploadSuccess) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//
//    public static boolean upload(String localFilePath, String remoteFilePath, String host, int port, String username, String password) throws JSchException, SftpException {
//
//        JSch jsch = new JSch();
//        Session session = null;
//        ChannelSftp channel = null;
//        try {
//            session = jsch.getSession(username, host, port);
//            session.setPassword(password);
//            session.setConfig("StrictHostKeyChecking", "no");
//            session.connect();
//
//            channel = (ChannelSftp) session.openChannel("sftp");
//            channel.connect();
//
//            System.out.println("mkdir "+remoteFilePath);
//
////            判断文件夹是否存在
//            if(!FilesIfexist.IsNotExist(session)){
//                System.out.println("文件夹不存在");
//                // 创建文件夹
//                CentosOperate.executeCommand(session,"mkdir "+remoteFilePath);
//            }
//
//            // 上传compose 文件
//            channel.put(localFilePath, remoteFilePath);
//
//            channel.disconnect();
//
////           判断docker 和 docker compose是否安装 false 重新安装 true直接部署
//            if(!isCommandAvailable(session,"docker")){
//                DockerAndComposeDeploy.deployDockerCompose(session);
//            }
//
//            if(!isCommandAvailable(session,"docker compose")){
//                executeCommand(session, "sudo yum install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin");
//            }
//
//            //执行docker compose 部署命令
//            deployMiddleware.deploy(session,"docker compose -f "+remoteFilePath+"/docker-compose.yaml up -d");
//
//            return true;
//        } catch (JSchException | SftpException e) {
//            throw new DeployException(201,e.getMessage());
//        } catch (Exception e) {
//            throw new DeployException(201,e.getMessage());
//        }
//    }


    public static void uploadFileV2(String localFilePath, String remoteFilePath){
//        SessionUtils instance = SessionUtils.getInstance();
        SessionUtils sessionUtils = new SessionUtils();
        ChannelSftp channel = null;
        try{
            channel = (ChannelSftp) sessionUtils.getSession().openChannel("sftp");
            channel.connect();

            System.out.println("mkdir "+remoteFilePath);

//            判断文件夹是否存在
            if(!FilesIfexist.IsNotExist(sessionUtils.getSession(),remoteFilePath)){
                System.out.println("文件夹不存在");
                // 创建文件夹
                CentosOperate.executeCommand(sessionUtils.getSession(),"mkdir "+remoteFilePath);
            }

            // 上传compose 文件
            channel.put(localFilePath, remoteFilePath);

            System.out.println("上传成功 ");
            channel.disconnect();
        }catch (Exception e){
            throw new DeployException(201,e.getMessage());
        }finally {
            sessionUtils.getSession().disconnect();
        }

    }



    // 递归上传文件夹
    public static void uploadFolder(Session session, ChannelSftp channel, String localFolderPath, String remoteFolderPath) throws SftpException {
        File localFolder = new File(localFolderPath);
        if (localFolder.isDirectory()) {

            // 判断文件夹是否存在
            if (!FilesIfexist.IsNotExist(session,remoteFolderPath)) {
                System.out.println("文件夹不存在");
                // 创建文件夹
                // 在远程服务器上创建相应的文件夹
                channel.mkdir(remoteFolderPath);
            }


            // 遍历本地文件夹中的文件和子文件夹
            File[] files = localFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        // 上传文件
                        channel.put(file.getAbsolutePath(), remoteFolderPath + "/" + file.getName(), ChannelSftp.OVERWRITE);
                    } else if (file.isDirectory()) {
                        // 递归上传子文件夹
                        uploadFolder(session,channel, file.getAbsolutePath(), remoteFolderPath + "/" + file.getName());
                    }
                }
            }
        }else{
            // 判断文件夹是否存在
            if (!FilesIfexist.IsNotExist(session,remoteFolderPath)) {
                System.out.println("文件夹不存在");
                // 创建文件夹
                // 在远程服务器上创建相应的文件夹
                channel.mkdir(remoteFolderPath);
            }
            channel.put(localFolderPath, remoteFolderPath);
        }
    }
}
