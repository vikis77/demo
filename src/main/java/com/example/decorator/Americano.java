package org.example.decorator;

/**
 * 美式咖啡 具体基础组件
 *
 * @Author liuyun
 * @Since 2025/2/28 15:31
 */
public class Americano implements Beverage{

    @Override
    public String getDescription()  {
        return "美式咖啡";
    }

    @Override
    public double getCost() {
        return 5.0;
    }
    
}
