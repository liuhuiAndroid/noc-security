package com.noc.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserQueryCondition {

    @ApiModelProperty(value = "用户名称")
    private String username;

    @ApiModelProperty(value = "用户年龄起始值")
    private int age;

    @ApiModelProperty(value = "用户年龄终止值")
    private int ageTo;

    @ApiModelProperty(value = "测试值")
    private String xxx;

}
