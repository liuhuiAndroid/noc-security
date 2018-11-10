package com.noc.validator;

import com.noc.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// ConstraintValidator第一个泛型是你要验证的注解，第二个泛型是你要验证的类型
// MyConstraintValidator类不用加注解，spring看到这个类实现了ConstraintValidator接口，会自动变成bean
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    @Autowired
    private HelloService helloService;

    /**
     * 初始化方法
     * @param constraintAnnotation
     */
    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("my validator init");
    }

    /**
     * 真正的校验逻辑
     * @param value 校验的值
     * @param context 校验上下文
     * @return 返回true，代表校验成功
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        helloService.greeting("noc");
        System.out.println(value);
        return false;
    }

}