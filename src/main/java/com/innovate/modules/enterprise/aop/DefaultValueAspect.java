package com.innovate.modules.enterprise.aop;

import com.innovate.common.exception.RRException;
import com.innovate.modules.enterprise.annotation.DefaultValue;
import com.innovate.modules.enterprise.annotation.HasRole;
import com.innovate.modules.enterprise.enums.DefValueEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description AOP处理请求数据中指定值为空是赋值初始值
 * @date 2019/10/9
 */
@Aspect
@Component
public class DefaultValueAspect {

    @Pointcut("@annotation(com.innovate.modules.enterprise.annotation.DefaultValue)")
    public void logPointCut() {

    }

    @Around(value = "logPointCut()")
    public Object nullValueAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        DefaultValue defaultValue = targetMethod.getAnnotation(DefaultValue.class);
        if(defaultValue != null){
            Class<?> targetType = defaultValue.targetType();
            int index = defaultValue.index();
            Object[] args = joinPoint.getArgs();
            if(args != null && args.length > 0){
                for (int i=0; i<args.length; i++){
                    if (invokeMethod(defaultValue, index == i, args[i])) break;
                }
            }
        }
        //执行方法
        Object result = joinPoint.proceed();
        return result;
    }

    private boolean invokeMethod(DefaultValue defaultValue, boolean b, Object arg) {
        if(b){
            if(arg instanceof java.util.Map){
                java.util.Map map = (java.util.Map) arg;
                Object object = map.get(defaultValue.key());
                if (null == object) {
                    DefValueEnum defValueEnum = defaultValue.defValueEnum();
                    enumType(defValueEnum, defaultValue, map);
                }
            }
            return true;
        }
        return false;
    }

    private void enumType(DefValueEnum defValueEnum, DefaultValue defaultValue, Map map) {
        switch (defValueEnum){
            case BYTE:
                map.put(defaultValue.key(),Byte.valueOf(defaultValue.defValue()));
                break;
            case SHORT:
                map.put(defaultValue.key(),Short.valueOf(defaultValue.defValue()));
                break;
            case INTEGER:
                map.put(defaultValue.key(),Integer.valueOf(defaultValue.defValue()));
                break;
            case LONG:
                map.put(defaultValue.key(),Long.valueOf(defaultValue.defValue()));
                break;
            case FLOAT:
                map.put(defaultValue.key(),Float.valueOf(defaultValue.defValue()));
                break;
            case DOUBLE:
                map.put(defaultValue.key(),Double.valueOf(defaultValue.defValue()));
                break;
            case BOOLEAN:
                map.put(defaultValue.key(),Boolean.valueOf(defaultValue.defValue()));
                break;
            case STRING:
                map.put(defaultValue.key(),defaultValue.defValue());
                break;
        }
    }
}
