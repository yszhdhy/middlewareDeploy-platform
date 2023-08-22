package com.example.middlewaredeploy;

import com.example.middlewaredeploy.nettyServer.MiddlewareServer;
import com.example.middlewaredeploy.service.MiddlewareService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MiddlewareDeployApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MiddlewareDeployApplication.class, args);
        // 在应用程序启动后执行打开浏览器的操作
        openBrowser("http://localhost:9999/admin/login");

//        开启nettyServer
        MiddlewareServer.start();
    }

    /**
     * @author CaptureOrNew
     * @description 获取本地浏览器并自动打开项目首页
     * @date 16:30:43 2023/7/16
     * @param url
     **/
    private static void openBrowser(String url) {
        try {
            // 根据不同操作系统执行不同的命令
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.contains("mac")) {
                // macOS
                Runtime.getRuntime().exec("open " + url);
            } else if (os.contains("nix") || os.contains("nux")) {
                // Linux
                Runtime.getRuntime().exec("xdg-open " + url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
