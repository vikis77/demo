package org.example.reflect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *  反射基础 示例类
 *
 * @Author liuyun
 * @Since 2025/3/5 15:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyClass {
    private String name;

    private Integer age;

    // 私有构造方法
    private MyClass(String name) {
        this.name = name;
    }

    // 私有方法
    private void sayHello(String person) {
        System.out.println(name + "对 " + person + " Say Hello!");
    }
}

