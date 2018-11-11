package com.noc.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.noc.validator.MyConstraint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

@ApiModel
public class User {

    @ApiModelProperty(value = "用户id")
    private String id;

    @MyConstraint(message = "这是一个测试")
    @ApiModelProperty(value = "用户名称")
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "用户密码")
    private String password;

    @Past(message = "生日必须是过去的时间")
    @ApiModelProperty(value = "用户生日")
    private Date birthday;

    /**
     * @JsonView 简单视图
     */
    public interface UserSimpleView {

    }

    /**
     * @JsonView 详细视图
     */
    public interface UserDetailView extends UserSimpleView {

    }

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
