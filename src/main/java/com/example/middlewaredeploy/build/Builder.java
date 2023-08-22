package com.example.middlewaredeploy.build;


import com.example.middlewaredeploy.entity.to.ShellFile;

public abstract class Builder {

    protected ShellFile file = new ShellFile();

    public abstract void builderShell();

    public abstract ShellFile createShell();
}
