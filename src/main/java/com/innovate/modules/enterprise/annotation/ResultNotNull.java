package com.innovate.modules.enterprise.annotation;

import java.lang.annotation.*;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description 返回值非空处理
 * @date 2019/9/26
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResultNotNull {

    String name() default "";

    Class clazz();

    String[] keyValues() default {};

    String[] resultValues() default {};

    String targetKey();

    String codeKey();

    int errorCode();

}
