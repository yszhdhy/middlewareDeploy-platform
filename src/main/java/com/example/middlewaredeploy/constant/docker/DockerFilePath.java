package com.example.middlewaredeploy.constant.docker;


/**
 * @project middlewareDeploy
 * @description 关于docker 的文件路径
 * @author capture or new
 * @date 2023/7/19 11:44:43
 * @version 1.0
 */

public enum DockerFilePath {
    DOCKER_COMPOSE_PATH("docker-compose.yaml"),
    DOCKERFILE_PATH("Dockerfile"),

    DOCKERFILE_REMOTE_PATH("/opt/dockerfile")
    ;

    private String filePath;

    DockerFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }


}
