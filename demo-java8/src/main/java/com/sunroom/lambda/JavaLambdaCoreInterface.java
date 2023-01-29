package com.sunroom.lambda;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

/**
 * Java8内置的四大核心接口
 * <p>
 * Consumer<T>: 消费型接口
 * void accept(T t);
 * Supplier<T>: 供给型接口
 * T get();
 * Function<T, R>: 函数型接口
 * R apply(T t);
 * Predicate<T>: 断言型接口
 * boolean test(T t);
 */
public class JavaLambdaCoreInterface {

    // Consumer<T> 消费型接口
    @Test
    public void test1() {
        // 提前定义方法
        happy(10000, (m) -> System.out.println("消费[" + m + "]成功!"));

        System.out.println("===========");

        // 直接对接口进行Lambda实现
        Consumer<Double> consumer = (m) -> System.out.println("消费[" + m + "]成功!");
        consumer.accept(20000d);
    }

    private void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    // Supplier<T> 供给型接口
    @Test
    public void test2() {
        // 产生100以内的随机数
        Supplier<Integer> supplier = () -> (int) (Math.random() * 100);
        List<Integer> list = new ArrayList<>();
        for (Integer i = 0; i < 10; i++) {
            System.out.println("随机数" + i + ":" + supplier.get());
        }
    }

    // Function<T, R> 函数型接口
    @Test
    public void test3() {
        // 获取字符串长度
        Function<String, Integer> function = (str) -> str.length();
        System.out.println("字符串长度:" + function.apply("获取字符串的长度"));
    }

    // Predicate<T> 断言型接口
    @Test
    public void test4() {
        Predicate<Integer> predicate = age -> age > 20;
        System.out.println("年龄是否大于20岁:" + predicate.test(18));
    }
}
