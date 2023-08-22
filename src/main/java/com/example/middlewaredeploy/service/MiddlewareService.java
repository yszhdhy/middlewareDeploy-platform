package com.example.middlewaredeploy.service;

import com.example.middlewaredeploy.entity.dto.CategoryDto;
import com.example.middlewaredeploy.entity.dto.MiddlewareDto;

import java.util.List;

/**
 * @project middlewareDeploy
 * @description 中间件service
 * @author capture or new
 * @date 2023/7/17 11:42:22
 * @version 1.0
 */
public interface MiddlewareService {

    List<CategoryDto> getCategory();

    List<MiddlewareDto> getMiddlewares(Integer id);

//    List<MiddlewareDto> getListById(Integer id);
}
