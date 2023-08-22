package com.example.middlewaredeploy.constant;

/**
 * @project middlewareDeploy
 * @description redis Shell脚本
 * @author capture or new
 * @date 2023/7/1 10:26:07
 * @version 1.0
 */
public enum RedisShell implements Adapter{

    REDIS_SHELL("  redis:\n" +
            "    image: redis:%s\n" +
            "    restart: always\n" +
            "    volumes:\n" +
            "      - ./redis-data:/data\n" +
            "    ports:\n" +
            "      - 6379:6379" +
            "\n"),
    ;

    private final String command;

    RedisShell(String command) {
        this.command = command;
    }

    @Override
    public String getCommand() {
        return command;
    }
}
