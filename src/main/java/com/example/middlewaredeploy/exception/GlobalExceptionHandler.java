package com.example.middlewaredeploy.exception;


import com.example.middlewaredeploy.result.Result;
import com.example.middlewaredeploy.result.ResultCodeEnum;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;


@RestControllerAdvice
public class GlobalExceptionHandler {

    //全局异常处理，执行的方法
    @ExceptionHandler(Exception.class)
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail().message("执行全局异常处理...");
    }

    //特定异常处理
    @ExceptionHandler(ArithmeticException.class)
    public Result error(ArithmeticException e) {
        e.printStackTrace();
        return Result.fail().message("执行特定异常处理...");
    }

    //自定义异常处理
    @ExceptionHandler(DeployException.class)
    public Result error(DeployException e) {
        e.printStackTrace();
        return Result.fail().code(e.getCode()).message(e.getMsg());
    }


    /**
     * 执行参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class) //使用Exception 表示所有异常都能处理
    public Result handleVaildException(MethodArgumentNotValidException e)
    {
//        获取所有的异常
        BindingResult bindingResult = e.getBindingResult();
        HashMap<String, String> map = new HashMap<>();
        bindingResult.getFieldErrors().forEach((fieldError)->
        {
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
        });

        return Result.fail(map, ResultCodeEnum.VAILD_EXCEPTION);
    }
}
