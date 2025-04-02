package org.example.reflect.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.reflect.annotation.RequestMapping;

/**
 * 用户控制器
 *
 * @Author liuyun
 * @Since
 */
@Slf4j
public class UserController {

    // 使用自定义注解
    @RequestMapping("/login")
    public void login(String username) {
        log.info("用户{}登录成功", username);
    }
}
