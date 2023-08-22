package com.example.middlewaredeploy.constant.json;

/**
 * @project middlewareDeploy
 * @description json文件路径
 * @author capture or new
 * @date 2023/7/17 14:59:25
 * @version 1.0
 */

public enum JsonFilePath {
    CATEGORY_JSONFILE_PATH("data/category.json"),
    MIDDLEWARE_JSONFILE_PATH("data/middleware.json"),
    ;

    JsonFilePath(String filePath) {
        FilePath = filePath;
    }

    private String FilePath;

    public String getFilePath() {
        return FilePath;
    }
}
