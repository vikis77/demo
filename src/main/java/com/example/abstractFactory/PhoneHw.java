package org.example.abstractFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 华为手机
 *
 * @Author liuyun
 * @Since 2025/3/4 14:50
 */
@Slf4j
public class PhoneHw implements AbstractPhoneProduct{
    public void create() {
        log.info("创建华为手机");
    }
}
