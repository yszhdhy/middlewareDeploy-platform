package com.example.middlewaredeploy.utils;

import com.example.middlewaredeploy.constant.MysqlShell;
import com.example.middlewaredeploy.demo1;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @project middlewareDeploy
 * @description 数据读写yaml
 * @author capture or new
 * @date 2023/7/1 10:53:50
 * @version 1.0
 */
public class WriteYamlUtils {


    /**
     * @author CaptureOrNew
     * @description 重构yaml文件 加入新的配置
     * @param path yaml文件位置
     * @param map 配置
     * @param level 配置的层级 存放位置
     * @param dispositionName 配置名称
     **/

    public static void reconstitution(String path, Map<String, Object> map, String level, String dispositionName) {

        // 从YAML文件中加载数据到Java对象中
        InputStream input = null;
        try {
            input = new FileInputStream(path);
            Yaml yaml = new Yaml();
            Map<String, Object> obj = yaml.load(input);

            // 获取现有的services部分
            Map<String, Object> services = (Map<String, Object>) obj.get("services");
            if (services == null) {
                services = new LinkedHashMap<>();
                obj.put("services", services);
            }

            // 获取现有的volumes部分
            Map<String, Object> volumes1 = (Map<String, Object>) obj.get("volumes");
            if (volumes1 == null) {
                volumes1 = new LinkedHashMap<>();
                obj.put("volumes", volumes1);
            }

            Map<String, Object> yamlmap = null;
            Map<String, Object> yamlmap2 = null;
            yamlmap = obj;
            System.out.println(yamlmap + "======");
            yamlmap2 = obj;


            if (level != null) {
                String[] keys = level.split("\\.");
                if (keys.length == 1 && dispositionName == null) {
                    // 在没有key的时候就用原来的key
                    dispositionName = keys[0];

                } else {
                    for (String key : keys) {
                        yamlmap2 = (Map<String, Object>) yamlmap2.get(key);
                    }
                }

            }
            yamlmap2.put(dispositionName, map);
            input.close();
            System.out.println(yamlmap);
            File file = new File(path);
            boolean delete = file.delete();
            System.out.println(delete + "yaml已经被删除");
            if (delete) {
                // 将修改后的Java对象写回YAML格式
                write(path, yamlmap);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public static void write(String filePath, String shell) {

        // 将配置写入到文件中
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(shell);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void writeAppend(String filePath, String shell) {

        // 将配置写入到文件中
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(shell);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(String path, Map<String, Object> yamlData) {

        final DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN);

        //        将Java对象转换为YAML格式并写入文件：  true 表示是否以追加的形式进行写入
        try (FileWriter writer = new FileWriter(path, true)) {
            Yaml yaml = new Yaml(options);
            yaml.dump(yamlData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
