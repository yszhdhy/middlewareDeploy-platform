package com.example.middlewaredeploy.build.shellBuilder;

import com.example.middlewaredeploy.build.MiddlewareConfigStrategy;
import com.example.middlewaredeploy.constant.ElasticsearchShell;
import com.example.middlewaredeploy.constant.MysqlShell;
import com.example.middlewaredeploy.entity.dto.MiddlewareDto;
import com.example.middlewaredeploy.utils.WriteYamlUtils;

import static com.example.middlewaredeploy.constant.docker.DockerFilePath.DOCKER_COMPOSE_PATH;

/**
 * @project middlewareDeploy
 * @description Elasticsearch配置类
 * @author capture or new
 * @date 2023/7/1 09:05:25
 * @version 1.0
 */
public class ElasticsearchConfigStrategy implements MiddlewareConfigStrategy {

    @Override
    public void generateConfig(Object middleware) {

        // 转为对应的对象
        MiddlewareDto middlewareDto = (MiddlewareDto) middleware;

        for (ElasticsearchShell value : ElasticsearchShell.values()) {
            // 写入 docker compose中
            WriteYamlUtils.writeAppend(DOCKER_COMPOSE_PATH.getFilePath(),value.getCommand());
        }

    }
}
