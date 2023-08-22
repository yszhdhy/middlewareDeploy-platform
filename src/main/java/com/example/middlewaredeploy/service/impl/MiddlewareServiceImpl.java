package com.example.middlewaredeploy.service.impl;

import com.alibaba.fastjson2.TypeReference;
import com.example.middlewaredeploy.entity.dto.CategoryDto;
import com.example.middlewaredeploy.entity.dto.MiddlewareDto;
import com.example.middlewaredeploy.service.MiddlewareService;
import com.example.middlewaredeploy.utils.JsonFileReader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.middlewaredeploy.constant.json.JsonFilePath.*;

/**
 * @project middlewareDeploy
 * @description 实现类
 * @author capture or new
 * @date 2023/7/17 11:42:49
 * @version 1.0
 */
@Service
public class MiddlewareServiceImpl implements MiddlewareService {

    /**
     * @author CaptureOrNew
     * @description 获取所有的分类
     * @date 15:19:22 2023/7/19
     * @return java.util.List<com.example.middlewaredeploy.entity.dto.CategoryDto>
     **/
    @Override
    public List<CategoryDto> getCategory() {
        List<CategoryDto> categorys = JsonFileReader.readJsonFile(CATEGORY_JSONFILE_PATH.getFilePath(), new TypeReference<List<CategoryDto>>() {
        });
        return categorys;
    }


    /**
     * @author CaptureOrNew
     * @description 返回对应类型的中间件
     * @date 15:18:18 2023/7/19
     * @param id
     * @return java.util.List<com.example.middlewaredeploy.entity.dto.MiddlewareDto>
     **/

    @Override
    public List<MiddlewareDto> getMiddlewares(Integer id) {
        List<MiddlewareDto> middlewares = JsonFileReader.readJsonFile(MIDDLEWARE_JSONFILE_PATH.getFilePath(), new TypeReference<List<MiddlewareDto>>() {
        });
        //        过滤出 中间件类型的父Id 相同 也就是判别类型
        middlewares = middlewares.stream().filter(middlewareDto -> {
            return middlewareDto.getParentId() == id;
        }).collect(Collectors.toList());
        return middlewares;
    }

}
