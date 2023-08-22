package com.example.middlewaredeploy;

import com.example.middlewaredeploy.constant.MysqlShell;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class demo1 {
    public static void main(String[] args) throws URISyntaxException {
        // 使用ClassLoader加载资源文件
        URL resourceURL = demo1.class.getClassLoader().getResource("docker/docker-compose.yaml");
        if (resourceURL == null) {
            System.err.println("Resource file not found: " + "docker/docker-compose.yaml");
        }
        System.out.println(resourceURL.getPath());

        writeAppend("docker/docker-compose.yaml",MysqlShell.MYSQL_SHELL.getCommand());
    }

    public static void writeAppend(String resourcesPath, String shell) throws URISyntaxException {
        // 使用ClassLoader加载资源文件
        URL resourceURL = demo1.class.getClassLoader().getResource(resourcesPath);
        if (resourceURL == null) {
            System.err.println("Resource file not found: " + resourcesPath);
            return;
        }

        // 将资源文件的URL转换为绝对路径
        String resourcePath = Paths.get(resourceURL.toURI()).toFile().getAbsolutePath();

        // 将配置追加写入到文件中
        try (FileWriter writer = new FileWriter(resourcePath, true)) {
            writer.write(shell);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
