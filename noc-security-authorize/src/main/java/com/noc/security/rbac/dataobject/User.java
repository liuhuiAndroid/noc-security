package com.noc.security.rbac.dataobject;

import lombok.Data;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "m_user")
@Entity
public class User {

    @Id
    private Long id;

    /** 登录名. */
    private String loginName;

    /** 昵称. */
    private String nickName;

    /** 真实姓名. */
    private String realName;

    /** 身份证号. */
    private String identityNo;

    /** 加密key. */
    private String salt;

    /** 密码. */
    private String password;

    /** 电话号码. */
    private String mobile;

    /** 性别:1 男 2 女. */
    private Integer sex;

    /** 头像URL. */
    private String headIcon;

    /** 用户类型：1 平台管理用户 2 APP用户. */
    private Integer userType;

    /** 生日. */
    private Date birthday;

    /** 身高(cm). */
    private Double height;

    /** 体重(kg). */
    private Double weight;

    /** 是否会员：0 否 1 是. */
    private Integer flgMember;

    /** 用户状态：0 未冻结 1 冻结. */
    private Integer flgFreeze;

    /** 是否删除 0 不删 1 删除. */
    private Integer flgDeleted;

    /** 创建人. */
    private Integer createdBy;

    /** 创建时间. */
    private Date createTime;

    /** 更新人. */
    private Integer lastModifiedBy;

    /** 更新时间. */
    private Date lastModifiedTime;

    /** 会员ID. */
    private String bankMemberId;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
