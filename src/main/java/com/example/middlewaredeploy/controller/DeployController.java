package com.example.middlewaredeploy.controller;

import com.example.middlewaredeploy.constant.ServerDisposition;
import com.example.middlewaredeploy.entity.dto.MiddlewareDto;
import com.example.middlewaredeploy.entity.vo.AlwaysResponse;
import com.example.middlewaredeploy.entity.vo.ServerDispositionVo;
import com.example.middlewaredeploy.result.Result;
import com.example.middlewaredeploy.service.DeployService;
import com.example.middlewaredeploy.service.MiddlewareService;
import com.example.middlewaredeploy.utils.UploadFileUtils;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.example.middlewaredeploy.constant.docker.DockerFilePath.DOCKER_COMPOSE_PATH;

@RestController
@RequestMapping("/admin/deploy")
public class DeployController {

    @Autowired
    DeployService deployService;

    @Autowired
    MiddlewareService middlewareService;

    /**
     * @author CaptureOrNew
     * @description 用户选择的中间件
     * @date 17:03:05 2023/7/14
     * @param serverDisposition
     * @return com.example.middlewaredeploy.result.Result
     **/
    @PostMapping("serverDisposition")
    public Result serverDisposition(@RequestBody ServerDispositionVo serverDisposition) throws IOException {

        ServerDisposition.serverDispositionVo = serverDisposition;

        return Result.ok();
    }


//    /**
//     * @author CaptureOrNew
//     * @description 部署中间键
//     * @date 17:02:34 2023/7/14
//     * @param alwaysResponse
//     * @return java.lang.String
//     **/
//    @PostMapping("/")
//    public String deployv2(@RequestBody AlwaysResponse alwaysResponse) throws IllegalAccessException, JSchException, SftpException {
//        deployService.deploy(alwaysResponse);
//
//        return "";
//    }


    /**
     * @author CaptureOrNew
     * @description 中间件部署
     * @date 15:22:01 2023/7/19
     * @param middlewares
     * @return java.lang.String
     **/
    @PostMapping("/v2")
    public String deploy(@RequestBody List<MiddlewareDto> middlewares) throws Exception {
        deployService.deployV2(middlewares);

        return "";
    }


    /**
     * @author CaptureOrNew
     * @description 根据Id获取对应的中间件
     * @date 14:44:28 2023/7/27
     * @param id
     * @return com.example.middlewaredeploy.result.Result
     **/
    @GetMapping("/{id}")
    public Result listMiddleware(@PathVariable Integer id){
        List<MiddlewareDto> middlewares =middlewareService.getMiddlewares(id);

        return Result.ok(middlewares);
    }

    /**
     * @author CaptureOrNew
     * @description 部署java服务
     * @date 14:47:11 2023/7/27
     * @param middlewares
     * @return java.lang.String
     **/
    @PostMapping("/service")
    public String deployService(@RequestBody List<MiddlewareDto> middlewares) throws Exception {
//        deployService.deployV2(middlewares);
        deployService.deployService(middlewares);
        return "";
    }

}
