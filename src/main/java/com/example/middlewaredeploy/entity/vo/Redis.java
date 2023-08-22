package com.example.middlewaredeploy.entity.vo;


import com.example.middlewaredeploy.entity.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Redis extends BaseEntity {
    private String name = "redis";
    private Integer isUserName = 0;
    private Integer isPassword = 1;
}
