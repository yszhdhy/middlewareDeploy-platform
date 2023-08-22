package com.example.middlewaredeploy.constant;

/**
 * @project middlewareDeploy
 * @description Dcokerfile 部署脚本
 * @author capture or new
 * @date 2023/7/24 16:09:58
 * @version 1.0
 */
public enum DockerfileShell implements Adapter {


    DOCKERFILE_SHELL("#基础镜像使用\n" +
            "FROM java:8\n" +
            "#作者\n" +
            "MAINTAINER %s\n" +
            "#VOLUME 指定临时文件目录为/tmp，在主机本目录之下创建一个临时文件并连接到容器的/tmp\n" +
            "VOLUME /tmp\n" +
            "#将jar包添加到容器中并更名为  \n" +
            "ADD %s %s\n" +
            "#运行jar包\n" +
            "RUN bash -c 'touch /%s'\n" +
            "ENTRYPOINT [\"java\",\"-jar\",\"/%s\"]\n" +
            "#暴露端口 和springboot工程中的yml文件中的端口进行一致  注意：记得开放安全组\n" +
            "EXPOSE %s"),


    DOCKERFILE_BUILDER_SHELL("docker build -t %s %s"),
    DOCKERFILE_RUN_SHELL("docker run -d -p %s:%s %s"),
    ;

    private final String command;

    DockerfileShell(String command) {
        this.command = command;
    }
    @Override
    public String getCommand() {
        return command;
    }
}
