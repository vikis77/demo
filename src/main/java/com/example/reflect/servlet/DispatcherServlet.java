package org.example.reflect.Servlet;

import lombok.extern.slf4j.Slf4j;
import org.example.reflect.annotation.RequestMapping;
import org.example.reflect.controller.UserController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义的 控制器servlet
 *
 * @Author liuyun
 * @Since
 */
@Slf4j
public class DispatcherServlet {

    // 请求映射: url -> method
    private Map<String, Method> handlerMapping = new HashMap<>();
    private Object controller;

    // 构造器：初始化请求映射，遍历当前控制器类的方法，找到使用了@RequestMapping注解的方法，并记录映射关系
    public DispatcherServlet() {
        controller = new UserController();
        for (Method method : controller.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(RequestMapping.class)) {
                String path = method.getAnnotation(RequestMapping.class).value();
                handlerMapping.put(path, method);
            }
        }
    }

    // 处理请求：根据请求的url和参数，找到对应的方法，并使用invoke执行
    public void doDispatch(String url, String param) throws InvocationTargetException, IllegalAccessException {
        if (handlerMapping.containsKey(url)) {
            handlerMapping.get(url).invoke(controller, param);
        } else {
            log.info("404 Not Found");
        }
    }
}
