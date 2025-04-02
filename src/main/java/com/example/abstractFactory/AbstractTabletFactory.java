package org.example.abstractFactory;

/**
 * 平板电脑工厂 接口
 *
 * @Author liuyun
 * @Since 2025/3/4 11:14
 */
public interface AbstractTabletFactory {
    // 生产平板电脑
    AbstractTabletProduct createTable();
    // 生产手机
    AbstractPhoneProduct createPhone();
}
