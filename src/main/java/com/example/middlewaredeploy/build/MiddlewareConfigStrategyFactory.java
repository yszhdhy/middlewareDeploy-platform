package com.example.middlewaredeploy.build;

import java.net.URISyntaxException;

/**
 * @project middlewareDeploy
 * @description 中间件配置策略工厂
 * @author capture or new
 * @date 2023/7/1 09:07:24
 * @version 1.0
 */
public class MiddlewareConfigStrategyFactory {

    //    聚合策略类对象
    private MiddlewareConfigStrategy strategy;

    public MiddlewareConfigStrategyFactory(MiddlewareConfigStrategy strategy) {
        this.strategy = strategy;
    }

    public void getStrategy(Object middleware) throws URISyntaxException {

        strategy.generateConfig(middleware);
    }

}
