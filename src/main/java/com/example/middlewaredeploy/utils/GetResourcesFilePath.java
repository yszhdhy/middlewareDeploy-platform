package com.example.middlewaredeploy.utils;


import com.example.middlewaredeploy.demo1;

import java.io.InputStream;
import java.net.URL;

/**
 * @project middlewareDeploy
 * @description 获取resources目录下的文件路径  也就是target 下的
 * @author capture or new
 * @date 2023/7/19 13:13:14
 * @version 1.0
 */
public class GetResourcesFilePath {

    public static String getPath(String resourcesPath){
        // 使用ClassLoader加载资源文件
        URL resourceURL = demo1.class.getClassLoader().getResource(resourcesPath);
        if (resourceURL == null) {
            return "Resource file not found: " + "docker/docker-compose.yaml";
        }
        return resourceURL.getPath();
    }
}
