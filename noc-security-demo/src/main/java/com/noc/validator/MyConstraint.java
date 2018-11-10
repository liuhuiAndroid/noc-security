package com.noc.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @Target 指定注解可以标注在方法和字段上
@Target({ElementType.METHOD, ElementType.FIELD})
// 运行时注解
@Retention(RetentionPolicy.RUNTIME)
// 用什么样的类校验
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {

    // 这三个属性是必须要写的，可以从 @NotBlank 复制
    // 校验不通过的提示信息
    String message();
    // 没有详细介绍
    Class<?>[] groups() default { };
    // 没有详细介绍
    Class<? extends Payload>[] payload() default { };

    String field() default "";
}
