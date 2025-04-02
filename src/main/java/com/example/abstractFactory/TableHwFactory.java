package org.example.abstractFactory;

/**
 * 具体 华为 工厂
 *
 * @Author liuyun
 * @Since 2025/3/4 11:17
 */
public class TableHwFactory implements AbstractTabletFactory {

    // 返回具体产品：华为平板电脑
    @Override
    public AbstractTabletProduct createTable() {
        return new TableHw();
    }

    // 返回具体产品：华为手机
    @Override
    public AbstractPhoneProduct createPhone() {
        return new PhoneHw();
    }

}
