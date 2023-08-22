package com.example.middlewaredeploy.utils;

import com.example.middlewaredeploy.constant.DockerShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.middlewaredeploy.utils.CentosOperate.executeCommand;

/**
 * @project middlewareDeploy
 * @description 部署docker和compose
 * @author capture or new
 * @date 2023/7/14 19:41:10
 * @version 1.0
 */
public class DockerAndComposeDeploy {

    public static void deployDockerCompose(Session session){
        try {

            // 执行远程命令
            ArrayList<DockerShell> dockerShells = new ArrayList<>(Arrays.asList(DockerShell.values()));

            for (DockerShell dockerShell : dockerShells) {
                executeCommand(session, dockerShell.getCommand());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
