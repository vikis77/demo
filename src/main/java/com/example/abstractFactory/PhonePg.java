package org.example.abstractFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 苹果手机
 *
 * @Author liuyun
 * @Since 2025/3/4 14:48
 */
@Slf4j
public class PhonePg implements AbstractPhoneProduct{
    public void create() {
        log.info("生产了苹果手机");
    }
}
