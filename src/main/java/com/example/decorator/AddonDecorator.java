package org.example.decorator;

/**
 * 抽象装饰器
 *
 * @Author liuyun
 * @Since 2025/2/28 15:35
 */
abstract public class AddonDecorator implements Beverage{
    protected Beverage beverage;

    public AddonDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription();
    }

    @Override
    public double getCost() {
        return beverage.getCost();
    }
}
