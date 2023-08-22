package com.example.middlewaredeploy.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
/**
 * @project middlewareDeploy
 * @description 读取Json中的数据
 * @author capture or new
 * @date 2023/7/17 10:35:08
 * @version 1.0
 */
public class JsonFileReader {

    public static <T> List<T> readJsonFile(String filePath, TypeReference<List<T>> typeReference) {
        try {
            InputStream inputStream = JsonFileReader.class.getClassLoader().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }

            String jsonContent = content.toString();
            return JSON.parseObject(jsonContent, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
