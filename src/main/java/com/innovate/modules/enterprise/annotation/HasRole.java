package com.innovate.modules.enterprise.annotation;

import java.lang.annotation.*;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description aop实现角色分离
 * @date 2019/9/23
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HasRole {

    String name();

    String[] roles();

}
