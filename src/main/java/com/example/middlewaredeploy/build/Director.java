package com.example.middlewaredeploy.build;


import com.example.middlewaredeploy.entity.to.ShellFile;

public class Director {

    //声明 builder类型的变量

    public Builder builder;

    public Director(Builder builder){
        this.builder = builder;
    }

    //组装文件
    public ShellFile construct(){
        builder.builderShell();
        return builder.createShell();
    }
}
