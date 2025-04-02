package org.example.abstractFactory;

/**
 * 苹果工厂
 *
 * @Author liuyun
 * @Since 2025/3/4 11:44
 */
public class TablePgFactory implements AbstractTabletFactory {

    // 生产苹果平板
    @Override
    public AbstractTabletProduct createTable() {
        return new TablePg();
    }

    // 生产苹果手机
    @Override
    public AbstractPhoneProduct createPhone() {
        return new PhonePg();
    }
}
