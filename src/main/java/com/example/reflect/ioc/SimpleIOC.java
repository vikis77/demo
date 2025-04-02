package org.example.reflect.ioc;

import org.example.reflect.annotation.MyComponent;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * 简易 IOC容器
 *
 * @Author liuyun
 * @Since 2025/3/6 09:49
 */
public class SimpleIOC {
    // 使用Map作为容器，key为bean的id，value为bean的实例
    private Map<String, Object> beanMap = new HashMap<>();

    /**
     * 将目标类放到ICO容器中管理
     * 遍历当前包下所有java文件，如果文件名以.java结尾，则将文件名去掉.java后缀，拼接成类名，
     * 并且判断类是否被MyComponent注解标注，如果被标注，则将beanName作为key，bean实例作为value，
     * 将类放到自定义的beanMap容器中（模拟IOC容器）
     */
    public SimpleIOC(String packageName) {
        try {
            String path = packageName.replace(".", "/");
            File dir = new File("src/main/java/" + path);
            for (File file : dir.listFiles()) {
                if (file.getName().endsWith(".java")) {
                    String className = packageName + "." + file.getName().replace(".java", "");
                    Class<?> clazz = Class.forName(className);
                    if (clazz.isAnnotationPresent(MyComponent.class)) {
                        MyComponent myComponent = clazz.getAnnotation(MyComponent.class);
                        String beanName = myComponent.value();
                        Constructor<?> constructor = clazz.getDeclaredConstructor();
                        Object bean = constructor.newInstance();
                        beanMap.put(beanName, bean);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据类上注解值获取对应Bean示例
     */
    public Object getBean(String name) {
        return beanMap.get(name);
    }
}