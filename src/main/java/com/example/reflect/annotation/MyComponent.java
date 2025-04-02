package org.example.reflect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 *
 * @Author liuyun
 * @Since 2025/3/6 09:43
 */
@Retention(RetentionPolicy.RUNTIME) // 注解保留到运行时
@Target(ElementType.TYPE) // 作用于类
public @interface MyComponent {
    String value() default ""; // 注解的值
}
