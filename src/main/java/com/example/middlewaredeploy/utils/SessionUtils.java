package com.example.middlewaredeploy.utils;

import com.example.middlewaredeploy.constant.ServerDisposition;
import com.example.middlewaredeploy.entity.vo.ServerDispositionVo;
import com.example.middlewaredeploy.exception.DeployException;
import com.jcraft.jsch.*;
import lombok.Data;

/**
 * @project middlewareDeploy
 * @description 获取session
 * @author capture or new
 * @date 2023/7/27 15:35:56
 * @version 1.0
 */
@Data
public class SessionUtils {

    private Session session = null;

    public SessionUtils(){
        ServerDispositionVo serverDispositionVo = ServerDisposition.serverDispositionVo;

        JSch jsch = new JSch();
        try {
            session = jsch.getSession(serverDispositionVo.getUserName(), serverDispositionVo.getHost(), serverDispositionVo.getPort());
            session.setPassword( serverDispositionVo.getPassword());
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
        } catch (JSchException e) {
            throw new DeployException(201,e.getMessage());
        } catch (Exception e) {
            throw new DeployException(201,e.getMessage());
        }
    }

//    //定义一个静态内部类
//    private static class SingletonHolder{
//        //在内部类中声明并初始化外部类的对象
//        private static SessionUtils sessionUtils = new SessionUtils();
//    }

    //提供公共的访问方式
//    public static SessionUtils getInstance(){
//        return new SessionUtils();
//    }

}
