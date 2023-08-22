package com.example.middlewaredeploy;

import com.example.middlewaredeploy.constant.MysqlShell;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileWriter;
import java.io.IOException;

@SpringBootTest
class MiddlewareDeployApplicationTests {

    @Test
    void contextLoads() {
        // 获取 DEMO 枚举常量的配置
        String demoConfig = MysqlShell.MYSQL_SHELL.getCommand();

        // 将配置写入到文件中
        try (FileWriter writer = new FileWriter("docker-compose.yaml",true)) {
            writer.write(demoConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
