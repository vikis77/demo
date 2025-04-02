package org.example.decorator;

/**
 * 配料：牛奶类
 *
 * @Author liuyun
 * @Since 2025/2/28 15:38
 */
public class Milk extends AddonDecorator{
    public Milk(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "+ 牛奶";
    }

    @Override
    public double getCost() {
        return beverage.getCost() + 2.0;
    }
}
