package org.example.abstractFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * 苹果平板电脑
 *
 * @Author liuyun
 * @Since 2025/3/4 11:42
 */
@Slf4j
public class TablePg implements AbstractTabletProduct{
    @Override
    public void create() {
        log.info("创建-苹果-平板电脑");
    }
}
