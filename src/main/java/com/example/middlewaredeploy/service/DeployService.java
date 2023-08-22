package com.example.middlewaredeploy.service;


import com.example.middlewaredeploy.entity.dto.MiddlewareDto;
import com.example.middlewaredeploy.entity.vo.AlwaysResponse;
import com.example.middlewaredeploy.entity.vo.ServerDispositionVo;
import com.example.middlewaredeploy.result.Result;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import java.util.List;
import java.util.Map;

public interface DeployService {
    void deploy(AlwaysResponse alwaysResponse) throws IllegalAccessException, JSchException, SftpException;

    String connect();

    void deployV2(List<MiddlewareDto> middlewares) throws Exception;

    void deployService(List<MiddlewareDto> middlewares) throws Exception;
}
