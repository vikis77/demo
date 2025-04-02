# 设计模式之 工厂模式（工厂方法、抽象工厂） 大白话版

一段话总结：我的理解是，工厂方法模式就是一个产品对应一个工厂，有一个抽象的工厂接口和一个抽象的产品接口，具体产品实现接口产品，具体工厂实现接口工厂，工厂有一个方法可以生产产品（即方法返回一个产品），当有一个新的产品出现时，就需要创建一个新的工厂与之对应。而抽象工厂的区别就是在工厂方法的基础上变为一个工厂可以生产出多个产品。

---
定义：工厂模式就是我需要的 **对象（产品）** 都是从工厂（类）中创建出来的。我们需要什么产品就去什么工厂拿就完了。

工厂模式里面又分为 工厂方法模式和抽象工厂模式。 我们只需要抓住 两个具体类、两个抽象类 来理解就可以了。
即：
- 具体产品、具体工厂。
- 抽象产品、抽象工厂。（这里的抽象指的是 它是一个接口）

## 工厂方法模式（Factory Method）
有一个产品接口和一个工厂接口。一个产品接口可以有多个具体的产品，一个工厂接口可以有多个具体工厂。
> 产品接口
> 
> |---------> 具体产品A
> 
> |---------> 具体产品B


> 工厂接口
>
> |---------> 具体工厂A
> 
> |---------> 具体工厂B

这里有个限制，每个工厂只能一种产品，即产品和工厂需要一一对应，有多少产品就得有多少个工厂。

这里我们抛弃掉“我们自己生产产品（自己new一个产品出来）”这个概念，产品只能通过工厂来生产。
工厂可以生产产品，实际上指的是工厂类有一个方法，可以返回一个产品对象。

现在可以很容易的理解下面这段“工厂方法模式”的代码了。
示例代码：
```java
// 产品接口
interface Product {
    void create();
}

// 具体产品A
class ConcreteProductA implements Product {
    @Override
    public void create() {
        System.out.println("Creating Product A");
    }
}

// 具体产品B
class ConcreteProductB implements Product {
    @Override
    public void create() {
        System.out.println("Creating Product B");
    }
}

// 工厂接口
interface Creator {
    Product createProduct();
}

// 具体工厂A
class ConcreteCreatorA implements Creator {
    @Override
    public Product createProduct() {
        return new ConcreteProductA();
    }
}

// 具体工厂B
class ConcreteCreatorB implements Creator {
    @Override
    public Product createProduct() {
        return new ConcreteProductB();
    }
}

// 客户端代码
public class Main {
    public static void main(String[] args) {
        Creator creatorA = new ConcreteCreatorA();
        Product productA = creatorA.createProduct();
        productA.create();

        Creator creatorB = new ConcreteCreatorB();
        Product productB = creatorB.createProduct();
        productB.create();
    }
} 
```

这么设计的意义在哪里？我们不需要关注产品的创建过程，只需要创建工厂类就能拿到产品。
这并不是多此一举，试想一下，如果产品创建过程复杂，那么我们只需要创建一个工厂类来创建产品，把负责的工作都封装在工厂类中，这样客户端代码就比较简洁了。

现在我们已经完全理解并掌握工厂方法模式了。那么**抽象工厂模式**对于我们来说就变得很简单了。


## 抽象工厂模式（Abstract Factory）
提问：还记得开头说的“两个具体”“两个抽象”是什么吗？
这里我们还是需要抓住这两个概念帮助理解。

抽象工厂模式就是**一个工厂可以生产多个产品**。
请看VCR，示例代码：
```java
// 抽象产品A
interface ProductA {
    void createA();
}

// 抽象产品B
interface ProductB {
    void createB();
}

// 具体产品A1
class ConcreteProductA1 implements ProductA {
    @Override
    public void createA() {
        System.out.println("Creating Product A1");
    }
}

// 具体产品B1
class ConcreteProductB1 implements ProductB {
    @Override
    public void createB() {
        System.out.println("Creating Product B1");
    }
}

// 具体产品A2
class ConcreteProductA2 implements ProductA {
    @Override
    public void createA() {
        System.out.println("Creating Product A2");
    }
}

// 具体产品B2
class ConcreteProductB2 implements ProductB {
    @Override
    public void createB() {
        System.out.println("Creating Product B2");
    }
}

// 抽象工厂
interface AbstractFactory {
    ProductA createProductA();
    ProductB createProductB();
}

// 具体工厂1
class ConcreteFactory1 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ConcreteProductA1();
    }

    @Override
    public ProductB createProductB() {
        return new ConcreteProductB1();
    }
}

// 具体工厂2
class ConcreteFactory2 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        return new ConcreteProductA2();
    }

    @Override
    public ProductB createProductB() {
        return new ConcreteProductB2();
    }
}

// 客户端代码
public class Main {
    public static void main(String[] args) {
        AbstractFactory factory1 = new ConcreteFactory1();
        ProductA productA1 = factory1.createProductA();
        ProductB productB1 = factory1.createProductB();
        productA1.createA();
        productB1.createB();

        AbstractFactory factory2 = new ConcreteFactory2();
        ProductA productA2 = factory2.createProductA();
        ProductB productB2 = factory2.createProductB();
        productA2.createA();
        productB2.createB();
    }
}
```

有的同学这时候就有疑惑了（包括初学者的我），

在抽象工厂中为什么里面两个产品需要两个工厂，一个工厂明明就可以生产两个不同的产品了？？

这个问题的关键在于对“产品族”和“产品等级”的理解。

产品族：一组相关的产品，"相关"指的是同一个工厂生产的或者属于同一家公司的不同产品。例如：小米这家公司，他家的产品会有小米手机、小米平板、小米电视
，**这些产品一起就称为一个产品族**，都是由小米工厂生产出来的。我们可以简单理解为**一家公司的不同品类的产品集合**就是产品族了。

**不同的工厂代表的是不同的企业**，他们都有手机，平板，电视这些产品。

> 补充：
产品等级：可以理解为不同的品类的产品集合。例如：手机(包括了华为手机、小米手机、苹果手机等等），电脑(包括了戴尔电脑、惠普电脑、华硕电脑等等)，电视（包括了海信电视、小米电视、TCL电视等等）。
**手机、电脑、电视构成一个产品等级**。
![img_2.png](img_2.png)

现在上面的代码就变成这样：
```java
// 抽象产品A：手机
interface ProductA {
    void createA();
}

// 抽象产品B：电脑
interface ProductB {
    void createB();
}

// 具体产品A1：小米手机
class ConcreteProductA1 implements ProductA {
    @Override
    public void createA() {
        System.out.println("Creating Product A1");
    }
}

// 具体产品B1：小米电脑
class ConcreteProductB1 implements ProductB {
    @Override
    public void createB() {
        System.out.println("Creating Product B1");
    }
}

// 具体产品A2：华为手机
class ConcreteProductA2 implements ProductA {
    @Override
    public void createA() {
        System.out.println("Creating Product A2");
    }
}

// 具体产品B2：华为电脑
class ConcreteProductB2 implements ProductB {
    @Override
    public void createB() {
        System.out.println("Creating Product B2");
    }
}

// 抽象工厂
interface AbstractFactory {
    // 小米工厂和华为工厂 都能生产手机
    ProductA createProductA();
    // 小米工厂和华为工厂 都能生产电脑
    ProductB createProductB();
}

// 具体工厂1：小米工厂
class ConcreteFactory1 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        // 生产小米手机
        return new ConcreteProductA1();
    }
    
    @Override
    public ProductB createProductB() {
        // 生产小米电脑
        return new ConcreteProductB1();
    }
}

// 具体工厂2：华为工厂
class ConcreteFactory2 implements AbstractFactory {
    @Override
    public ProductA createProductA() {
        // 生产华为手机
        return new ConcreteProductA2();
    }

    @Override
    public ProductB createProductB() {
        // 生产华为电脑
        return new ConcreteProductB2();
    }
}

// 客户端代码
public class Main {
    public static void main(String[] args) {
        // 创建一个小米工厂
        AbstractFactory factory1 = new ConcreteFactory1();
        ProductA productA1 = factory1.createProductA(); // 创建小米手机
        ProductB productB1 = factory1.createProductB(); // 创建小米电脑
        productA1.createA();
        productB1.createB();

        // 创建华为工厂
        AbstractFactory factory2 = new ConcreteFactory2();
        ProductA productA2 = factory2.createProductA(); // 创建华为手机
        ProductB productB2 = factory2.createProductB(); // 创建华为电脑
        productA2.createA();
        productB2.createB();
    }
}
```
现在我们想想如何增加一个工厂呢？ **增加一个具体的工厂类，实现抽象工厂接口，然后实现创建不同产品族（手机、电脑、电视）的方法。**

如何给这些工厂增加一种新的产品C呢？**新增一个抽象产品C接口，抽象工厂里面增加一个产品C的抽象方法，每个具体的工厂都增加一个产品C具体方法。**

## 最后说说工厂模式的优缺点
优点：
- 封装性好：客户端不需要知道具体的产品类，只需要与工厂接口进行交互，遵循了“依赖抽象，不依赖具体”的原则。
- 解耦合：创建产品的过程被工厂类封装，客户端代码与具体的产品类解耦。
- 扩展性强：如果需要增加新产品，只需要扩展工厂类和产品类，不需要修改客户端代码。

缺点：
- 类的个数增加：每增加一个产品就需要增加一个具体工厂类，可能会使系统变得复杂。
- 不适用于所有场景：当产品种类较少时，工厂模式可能会显得过于复杂。