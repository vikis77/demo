package org.example.reflect.services.impl;


import org.example.reflect.annotation.MyComponent;
import org.example.reflect.services.UserService;

/**
 * 用户登录实现类
 *
 * @Author liuyun
 * @Since 2025/3/5 17:17
 */
@MyComponent("userService") // 自定义注解，将当前类交给自定义IOC容器管理
public class UserServiceImpl implements UserService {
    @Override
    public void login(String username, String password) {
        System.out.println(username + "正在登录.....");
    }
}
