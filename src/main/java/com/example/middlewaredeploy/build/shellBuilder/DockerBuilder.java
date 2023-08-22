package com.example.middlewaredeploy.build.shellBuilder;

import com.example.middlewaredeploy.build.Builder;
import com.example.middlewaredeploy.constant.DockerShell;
import com.example.middlewaredeploy.entity.to.ShellFile;

import java.util.ArrayList;
import java.util.Arrays;


public class DockerBuilder extends Builder {

    @Override
    public void builderShell() {
        ArrayList arrayList = new ArrayList<>(Arrays.asList(DockerShell.values()));
        file.setAdapterList(arrayList);
    }

    @Override
    public ShellFile createShell() {
        return file;
    }
}
