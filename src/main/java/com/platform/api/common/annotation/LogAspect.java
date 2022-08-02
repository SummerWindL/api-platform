package com.platform.api.common.annotation;

import java.lang.annotation.*;

/**
 * @author Advance
 * @date 2022年07月13日 23:46
 * @since V1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface LogAspect {
    String values() default "";

    String descript() default "自定义管理员注解";
}
