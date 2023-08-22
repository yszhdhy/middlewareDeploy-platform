package com.example.middlewaredeploy.build.shellBuilder;

import com.example.middlewaredeploy.build.MiddlewareConfigStrategy;
import com.example.middlewaredeploy.constant.RedisShell;
import com.example.middlewaredeploy.entity.dto.MiddlewareDto;
import com.example.middlewaredeploy.entity.vo.Redis;
import com.example.middlewaredeploy.utils.WriteYamlUtils;

import java.net.URISyntaxException;

import static com.example.middlewaredeploy.constant.docker.DockerFilePath.DOCKER_COMPOSE_PATH;

/**
 * @project middlewareDeploy
 * @description redis配置类
 * @author capture or new
 * @date 2023/7/1 09:06:03
 * @version 1.0
 */
public class RedisConfigStrategy implements MiddlewareConfigStrategy {

    @Override
    public void generateConfig(Object middleware){
        // 转为对应的对象
//        Redis redis = (Redis) middleware;
        MiddlewareDto middlewareDto = (MiddlewareDto) middleware;
//        格式化
        String redisShell = String.format(RedisShell.REDIS_SHELL.getCommand(), middlewareDto.getVersion());
        // 写入 docker compose中
        WriteYamlUtils.writeAppend(DOCKER_COMPOSE_PATH.getFilePath(),redisShell);
    }
}
