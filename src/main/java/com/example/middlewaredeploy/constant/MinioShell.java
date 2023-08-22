package com.example.middlewaredeploy.constant;

/**
 * @project middlewareDeploy
 * @description minio脚本
 * @author capture or new
 * @date 2023/7/24 16:09:58
 * @version 1.0
 */
public enum MinioShell implements Adapter {

    MINIO_SHELL("  minio:\n" +
            "    image: minio/minio:latest\n" +
            "    container_name: minio\n" +
            "    command: server /data --console-address \":9090\" --address \":9000\" \n" +
            "    volumes:\n" +
            "      - ./minio-data:/data  # 将 MinIO 数据目录映射到宿主机的 ./minio-data 目录\n" +
            "    ports:\n" +
            "      - \"9000:9000\"\n" +
            "    environment:\n" +
            "      - MINIO_ROOT_USER=%s \n" +
            "      - MINIO_ROOT_PASSWORD=%s"+
            "\n"),
    ;

    private final String command;

    MinioShell(String command) {
        this.command = command;
    }
    @Override
    public String getCommand() {
        return command;
    }
}
