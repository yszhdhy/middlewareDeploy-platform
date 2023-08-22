package com.example.middlewaredeploy.web;

import com.alibaba.fastjson2.JSON;

import com.example.middlewaredeploy.constant.ServerDisposition;
import com.example.middlewaredeploy.entity.dto.CategoryDto;
import com.example.middlewaredeploy.entity.dto.MiddlewareDto;
import com.example.middlewaredeploy.entity.vo.ServerDispositionVo;
import com.example.middlewaredeploy.result.Result;
import com.example.middlewaredeploy.service.DeployService;
import com.example.middlewaredeploy.service.MiddlewareService;

import com.example.middlewaredeploy.utils.CentosOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;

import java.util.List;


import static com.example.middlewaredeploy.constant.json.JsonFilePath.*;

/**
 * @project middlewareDeploy
 * @description 登录页面
 * @author capture or new
 * @date 2023/7/14 21:21:08
 * @version 1.0
 */
@Controller
@RequestMapping("/admin")
public class LoginMiddware {

    @Autowired
    DeployService deployService;

    @Autowired
    MiddlewareService middlewareService;

    /**
     * @author CaptureOrNew
     * @description 登录页面
     * @date 15:17:01 2023/7/19
     * @return java.lang.String
     **/
    @GetMapping("/login")
    public String login(){

        return "login";
    }

    /***
     * @author CaptureOrNew
     * @description 中间件页面
     * @date 15:16:44 2023/7/19
     * @param model
     * @return java.lang.String
     **/
    @GetMapping("/middleware")
    public String middleware(Model model) throws IOException {
        // TODO 校验 是否连接

        List<CategoryDto> categorys = middlewareService.getCategory();
        model.addAttribute("categorys",JSON.toJSONString(categorys));

        // 进入页面就获取初始数据
        List<MiddlewareDto> middlewares =  middlewareService.getMiddlewares(0);
        model.addAttribute("middlewares",JSON.toJSONString(middlewares));


        return "middleware";
    }


    /**
     * @author CaptureOrNew
     * @description 点击连接就会触发 判断是否连接成功 成功就跳跳转到中间件页面中
     * @date 15:15:53 2023/7/19
     * @param serverDisposition
     * @return com.example.middlewaredeploy.result.Result
     **/
    @PostMapping("/serverDisposition")
    @ResponseBody
    public Result serverDisposition(@RequestBody ServerDispositionVo serverDisposition) throws IOException {
        ServerDisposition.serverDispositionVo = serverDisposition;
        String connect = deployService.connect();

        return Result.ok(connect);
    }
}
