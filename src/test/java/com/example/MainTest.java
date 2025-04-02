package org.example.decorator;

import org.example.abstractFactory.*;
import org.example.reflect.MyClass;
import org.example.reflect.Servlet.DispatcherServlet;
import org.example.reflect.ioc.SimpleIOC;
import org.example.reflect.proxy.UserServiceProxy;
import org.example.reflect.services.UserService;
import org.example.reflect.services.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;

/**
 * 测试主类
 *
 * @Author liuyun
 * @Since 2025/2/28 15:55
 */
@SpringBootTest
public class MianTest {

    /**
     * 设计模式：装饰器模式
     */
    @Test
    public void test() {
        // 浓缩咖啡
        Beverage coffee = new Espresso();
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());
        // + 牛奶
        coffee = new Milk(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());
        // + 糖
        coffee = new Suger(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());
        System.out.println("--------------------------------------");
        Beverage coffee2 = new Espresso();
        coffee2 = new Milk(new Milk(coffee2));
        System.out.println(coffee2.getDescription() + " $" + coffee2.getCost());
    }

    /**
     * 设计模式：工厂模式
     */
    @Test
    public void test2() {
        // ------------ 华为工厂创建华为产品族 ------------
        AbstractTabletFactory tabletHwFactory = new TableHwFactory(); // 创建华为工厂
        AbstractTabletProduct table = tabletHwFactory.createTable(); // 华为工厂创建平板
        table.create();
        AbstractPhoneProduct phoneHw = tabletHwFactory.createPhone(); // 华为工厂创建手机
        phoneHw.create();

        // ------------ 苹果工厂创建苹果产品族 ------------
        AbstractTabletFactory tablePgFactory = new TablePgFactory(); // 创建苹果工厂
        table = tablePgFactory.createTable(); // 苹果工厂创建平板
        table.create();
        AbstractPhoneProduct phonePg = tablePgFactory.createPhone(); // 苹果工厂创建手机
        phonePg.create();

        // ------------ Java 中使用的工厂模式 ------------
//        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "password");
        Calendar calendar = Calendar.getInstance();
    }

    /**
     * 反射基础
     * 涉及类：
     *      org.example.reflect.MyClass
     * @Author liuyun
     * @Since 2025/3/5 15:49
     */
    @Test
    public void test3() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        // 1 获取类对象
        Class<?> clazz1 = Class.forName("org.example.reflect.MyClass"); // 1.1、通过“全类名”获取类对象

        MyClass obj = new MyClass(); // 1.2 通过“对象实例”获取类对象
        Class<?> clazz2 = obj.getClass();

        Class<?> clazz3 = MyClass.class; // 1.3 通过“类名.class"获取类对象

        // 2 创建对象
        Constructor<?> constructor1 = clazz1.getDeclaredConstructor(); // 获取无参构造
        Object instance1 = constructor1.newInstance(); // 并创建对象
        System.out.println(instance1);

        Constructor<?> constructor2 = clazz2.getDeclaredConstructor(String.class, Integer.class);// 获取有参构造
        Object instance2 = constructor2.newInstance("张三",20); // 并创建对象
        System.out.println(instance2);

        System.out.println("-------------------分割线1-----------------------");
        // 如果类的构造函数是私有的，需要设置“ accessible”为“true”
        // 1、获取类对象 这里使用上面的 clazz3
        // 2、获取“私有”构造函数
        Constructor<?> constructor3 = clazz3.getDeclaredConstructor(String.class);
//        Object instance3 = constructor3.newInstance("李四"); // 直接使用私有构造函数创建对象 报错：java.lang.IllegalAccessException: Class org.example.decorator.MianTest can not access a member of class org.example.reflect.MyClass with modifiers "private"
        constructor3.setAccessible(true); // 允许访问私有构造器
        Object instance3 = constructor3.newInstance("李四");
        System.out.println(instance3);

        System.out.println("-------------------分割线2-----------------------");
        // 访问类的字段
        Field field = clazz1.getDeclaredField("name");
        field.setAccessible(true);
        field.set(instance1,"王五");
        field.set(instance2,"赵六");
        field.set(instance3,"钱七");
        System.out.println(instance1+ "\n" + instance2+ "\n" + instance3);
        System.out.println(field.get(instance1)+ " " + field.get(instance2)+ " " + field.get(instance3));

        System.out.println("-------------------分割线3-----------------------");
        // 调用方法
        Method method = clazz2.getDeclaredMethod("sayHello", String.class);
        method.setAccessible(true);
        method.invoke(instance1,"小猫");
        method.invoke(instance2,"小狗");
    }

    /**
     * 反射应用 示例一：Java 的动态代理（日志增强）
     * 涉及类：org.example.reflect.services.impl.UserServiceImpl、org.example.reflect.proxy.UserServiceProxy
     * @Author liuyun
     * @Since 2025/3/5 17:02
     */
    @Test
    public void test4() {
        // 获取原对象
        UserService userService = new UserServiceImpl();
        // 获取代理对象
        UserService proxy = (UserService) UserServiceProxy.getProxy(userService);

        // 调用原对象方法
        userService.login("zhangsan", "123456");
        System.out.println("--------------对象分割----------------");
        // 调用代理对象方法
        proxy.login("zhangsan", "123456");
    }

    /**
     * 反射应用 示例二：反射在 Spring IOC 容器中的应用
     * 使用反射实现简易 IOC
     * 涉及类：org.example.reflect.ioc.SimpleIOC、org.example.reflect.services.impl.UserServiceImpl
     * @Author liuyun
     * @Since 2025/3/5 17:51
     */
    @Test
    public void test5() {
        SimpleIOC ioc = new SimpleIOC("org.example.reflect.services.impl");
        UserService userService = (UserService) ioc.getBean("userService");
        userService.login("qiqi", "123456");
    }

    /**
     * 反射应用 示例三：反射在 Spring MVC 的方法调用
     * 手写 Spring MVC 反射调用
     * 涉及类：
     *      org.example.reflect.annotation.RequestMapping、
     *      org.example.reflect.controller.UserController、
     *      org.example.reflect.Servlet.DispatcherServlet
     * @Author liuyun
     * @Since 2025/3/6 14:04
     */
    @Test
    public void test6() throws InvocationTargetException, IllegalAccessException {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.doDispatch("/login","滚动呆鹅");
    }
}