package com.example.middlewaredeploy.constant;

public enum MysqlShell implements Adapter{
    MYSQL_SHELL("  mysql:\n" +
            "    image: mysql:%s\n" +
            "    restart: always\n" +
            "    environment:\n" +
            "      MYSQL_ROOT_PASSWORD: %s\n" +
            "    volumes:\n" +
            "      - ./mysql-data:/var/lib/mysql\n" +
            "    ports:\n" +
            "      - 3306:3306" +
            "\n"),
    VOLUMES("  mysqldata:"),
    ;

    private final String command;

    MysqlShell(String command) {
        this.command = command;
    }

    @Override
    public String getCommand() {
        return command;
    }
}
