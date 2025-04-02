package org.example.decorator;

/**
 * 配料：糖类
 *
 * @Author liuyun
 * @Since 2025/2/28 15:52
 */
public class Suger extends AddonDecorator{
    public Suger(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "+ 糖";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }
}
