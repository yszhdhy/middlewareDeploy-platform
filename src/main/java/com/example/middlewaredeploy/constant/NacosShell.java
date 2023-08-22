package com.example.middlewaredeploy.constant;

/**
 * @project middlewareDeploy
 * @description minio脚本
 * @author capture or new
 * @date 2023/7/24 16:09:58
 * @version 1.0
 */
public enum NacosShell implements Adapter {

    NACOS_SHELL("  mall4cloud-nacos:\n" +
            "    image: nacos/nacos-server:v2.2.0-slim\n" +
            "    container_name: mall4cloud-nacos\n" +
            "    restart: always\n" +
            "    ports:\n" +
            "      - 8848:8848\n" +
            "      - 9848:9848\n" +
            "      - 9849:9849\n" +
            "    environment:\n" +
            "      - JVM_XMS=256m\n" +
            "      - JVM_XMX=256m\n" +
            "      - MODE=standalone\n" +
            "      - PREFER_HOST_MODE=hostname\n" +
            "      - SPRING_DATASOURCE_PLATFORM=mysql\n" +
            "      - MYSQL_SERVICE_HOST=%s\n" +
            "      - MYSQL_SERVICE_DB_NAME=%s\n" +
            "      - MYSQL_SERVICE_USER=%s\n" +
            "      - MYSQL_SERVICE_PASSWORD=%s\n" +
            "    volumes:\n" +
            "      - ./nacos/logs:/home/nacos/logs"),
    ;

    private final String command;

    NacosShell(String command) {
        this.command = command;
    }
    @Override
    public String getCommand() {
        return command;
    }
}
