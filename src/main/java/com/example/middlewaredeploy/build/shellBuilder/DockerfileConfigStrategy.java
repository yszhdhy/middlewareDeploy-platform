package com.example.middlewaredeploy.build.shellBuilder;

import com.example.middlewaredeploy.build.MiddlewareConfigStrategy;
import com.example.middlewaredeploy.constant.DockerfileShell;
import com.example.middlewaredeploy.constant.MinioShell;
import com.example.middlewaredeploy.entity.dto.MiddlewareDto;
import com.example.middlewaredeploy.utils.WriteYamlUtils;

import static com.example.middlewaredeploy.constant.docker.DockerFilePath.*;

/**
 * @project middlewareDeploy
 * @description dockerfile配置类
 * @author capture or new
 * @date 2023/7/1 09:05:25
 * @version 1.0
 */
public class DockerfileConfigStrategy implements MiddlewareConfigStrategy {

    @Override
    public void generateConfig(Object middleware) {

        // 转为对应的对象
        MiddlewareDto middlewareDto = (MiddlewareDto) middleware;

        String[] split = middlewareDto.getJarPath().split("\\\\");
        String jarName = split[split.length-1];

        System.out.println(split);
        System.out.println(jarName);
//        格式化
        String dockerfile = String.format(DockerfileShell.DOCKERFILE_SHELL.getCommand(),
                middlewareDto.getAuthor(),jarName,jarName.toLowerCase(),jarName.toLowerCase(),jarName.toLowerCase(),middlewareDto.getExpose());

        // 写入 docker compose中
        WriteYamlUtils.writeAppend(DOCKERFILE_PATH.getFilePath(),dockerfile);
    }
}
