package org.example.decorator;

/**
 * 浓缩咖啡 具体基础组件类
 *
 * @Author liuyun
 * @Since 2025/2/28 15:29
 */
public class Espresso implements Beverage{

    @Override
    public String getDescription() {
        return "浓缩咖啡";
    }

    @Override
    public double getCost() {
        return 1.99;
    }
}

