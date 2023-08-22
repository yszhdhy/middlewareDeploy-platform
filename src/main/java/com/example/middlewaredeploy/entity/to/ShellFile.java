package com.example.middlewaredeploy.entity.to;

import com.example.middlewaredeploy.constant.Adapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShellFile {

    List<Adapter> adapterList;
}
