package com.example.middlewaredeploy.build;

import java.net.URISyntaxException;

/**
 * @project middlewareDeploy
 * @description 中间件配置策略接口
 * @author capture or new
 * @date 2023/7/1 09:04:15
 * @version 1.0
 */
public interface MiddlewareConfigStrategy {
    void generateConfig(Object middleware) throws URISyntaxException;
}
