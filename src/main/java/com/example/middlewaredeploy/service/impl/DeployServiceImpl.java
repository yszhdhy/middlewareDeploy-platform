package com.example.middlewaredeploy.service.impl;


import com.example.middlewaredeploy.build.MiddlewareConfigStrategy;
import com.example.middlewaredeploy.build.MiddlewareConfigStrategyFactory;
import com.example.middlewaredeploy.constant.DockerfileShell;
import com.example.middlewaredeploy.entity.dto.MiddlewareDto;
import com.example.middlewaredeploy.entity.vo.AlwaysResponse;
import com.example.middlewaredeploy.entity.vo.ServerDispositionVo;
import com.example.middlewaredeploy.exception.DeployException;
import com.example.middlewaredeploy.service.DeployService;
import com.example.middlewaredeploy.utils.*;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static com.example.middlewaredeploy.constant.docker.DockerFilePath.*;
import static com.example.middlewaredeploy.utils.CentosOperate.executeCommand;
import static com.example.middlewaredeploy.utils.CentosOperate.isCommandAvailable;

@Service
public class DeployServiceImpl implements DeployService {

    @Override
    public void deploy(AlwaysResponse alwaysResponse) throws JSchException, SftpException {
        Field[] declaredFields = alwaysResponse.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true); // 设置字段可访问（包括私有字段）
            // 判断其中的字段是否为空
            Object value = null;
            try {
                value = declaredField.get(alwaysResponse);
                if (value != null) {
//                TODO    通过指定的路径（bug 并且实体类的名称和 Builder策略类的 前缀要相同） 使用反射机制 创建对象
                    System.out.println(declaredField.getType().getSimpleName() + "ConfigStrategy");
                    Class<?> mysqlEntityClass = Class.forName("com.example.middlewaredeploy.build.shellBuilder." + declaredField.getType().getSimpleName() + "ConfigStrategy");
                    Object mysqlEntityInstance = mysqlEntityClass.newInstance();
//                    通过工厂传递对象
                    MiddlewareConfigStrategyFactory middlewareConfigStrategyFactory = new MiddlewareConfigStrategyFactory((MiddlewareConfigStrategy) mysqlEntityInstance);
                    middlewareConfigStrategyFactory.getStrategy(value);
                }

            } catch (Exception e) {
                throw new DeployException(201, e.getMessage());
            }
        }

    }


    @Override
    public void deployV2(List<MiddlewareDto> middlewares) throws Exception {
        // 重置docker compose 文件
        WriteYamlUtils.write(DOCKER_COMPOSE_PATH.getFilePath(), "version: '3.9'\n" +
                "services:\n");

        //写入文件
        writeFile(middlewares);

        //上传文件
        UploadFileUtils.uploadFileV2(DOCKER_COMPOSE_PATH.getFilePath(),
                "/opt/dockerCompose");

//        获取session
        SessionUtils sessionUtils = new SessionUtils();

        //           判断docker 和 docker compose是否安装 false 重新安装 true直接部署
        if (!isCommandAvailable(sessionUtils.getSession(), "docker")) {
            DockerAndComposeDeploy.deployDockerCompose(sessionUtils.getSession());
        }

        if (!isCommandAvailable(sessionUtils.getSession(), "docker compose")) {
            executeCommand(sessionUtils.getSession(), "sudo yum install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin");
        }

        //执行docker compose 部署命令
        deployMiddleware.deploy(sessionUtils.getSession(), "docker compose -f /opt/dockerCompose/docker-compose.yaml up -d");

    }

    @Override
    public void deployService(List<MiddlewareDto> middlewares) throws Exception {
        // 重置dockerfile 文件
        WriteYamlUtils.write(DOCKERFILE_PATH.getFilePath(), "");
        writeFile(middlewares); //写入文件

        // 2、上传文件
        //上传文件
        UploadFileUtils.uploadFileV2(DOCKERFILE_PATH.getFilePath(),
                DOCKERFILE_REMOTE_PATH.getFilePath());

        String jarName = "";
        String expose = "";
        //上传文件
        for (MiddlewareDto middleware : middlewares) {
            UploadFileUtils.uploadFileV2(middleware.getJarPath(),
                    DOCKERFILE_REMOTE_PATH.getFilePath());

            String[] split = middleware.getJarPath().split("\\\\");
            jarName = split[split.length-1];
            expose = middleware.getExpose();
        }

        // 3、部署
        //        获取session
        SessionUtils sessionUtils = new SessionUtils();

        //           判断docker 和 docker compose是否安装 false 重新安装 true直接部署
        if (!isCommandAvailable(sessionUtils.getSession(), "docker")) {
            DockerAndComposeDeploy.deployDockerCompose(sessionUtils.getSession());
        }


        //执行docker compose 部署命令
        deployMiddleware.deploy(sessionUtils.getSession(),
                String.format(DockerfileShell.DOCKERFILE_BUILDER_SHELL.getCommand(),
                        jarName.toLowerCase().replace(".jar", ""),
                        DOCKERFILE_REMOTE_PATH.getFilePath()));

        deployMiddleware.deploy(sessionUtils.getSession(),
                String.format(DockerfileShell.DOCKERFILE_RUN_SHELL.getCommand(),expose,expose,
                        jarName.toLowerCase().replace(".jar","")));

    }


    public void writeFile(List<MiddlewareDto> middlewares) {

        if (middlewares != null) {
            for (MiddlewareDto middleware : middlewares) {
                try {
                    System.out.println(middleware.getIsSelect());
                    if (middleware.getIsSelect()) {
                        // 创建对象
                        Class<?> objectEntityClass = Class.forName("com.example.middlewaredeploy.build.shellBuilder." + middleware.getName() + "ConfigStrategy");
                        Object objectEntityInstance = objectEntityClass.newInstance();

//                    通过工厂传递对象
                        MiddlewareConfigStrategyFactory middlewareConfigStrategyFactory = new MiddlewareConfigStrategyFactory((MiddlewareConfigStrategy) objectEntityInstance);

                        middlewareConfigStrategyFactory.getStrategy(middleware);
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public String connect() {
        String connectMsg = ConnectTest.connect();
        return connectMsg;
    }


}
