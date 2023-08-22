package com.example.middlewaredeploy.entity.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AlwaysResponse {

    private MySQL mysql;
    private Redis redis;
}
