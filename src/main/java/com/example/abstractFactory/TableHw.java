package org.example.abstractFactory;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 具体 平板电脑
 *
 * @Author liuyun
 * @Since 2025/3/4 11:19
 */
@Slf4j
@Data
public class TableHw implements AbstractTabletProduct{
    @Override
    public void create() {
        log.info("创建-华为-平板电脑");
    }

}
