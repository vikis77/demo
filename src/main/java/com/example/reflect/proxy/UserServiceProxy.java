package org.example.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用户服务代理
 *
 * @Author liuyun
 * @Since 2025/3/5 17:17
 */
public class UserServiceProxy {

    /**
     * 获取代理对象
     *
     * @Author liuyun
     * @Since 2025/3/5 17:18
     */
    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
                        // 方法前操作
                        System.out.println("[日记] 方法" + method.getName() + "开始执行");
                        // 执行目标方法
                        Object result = method.invoke(target, args);
                        // 方法后操作
                        System.out.println("[日记] 方法" + method.getName() + "执行完毕");
                        return result;
                    }
                }
        );
    }
}











