package com.sunroom.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * 一、Lambda表达式的基础语法：
 *     java8中引入了新的操作符“->”，称为箭头操作符或Lambda操作符
 *     箭头操作符将Lambda表达式拆分成两部分
 *     左侧：Lambda表达式的参数列表
 *     右侧：Lambda表达式中所需要执行的功能，Lambda体
 *
 *     语法格式一：无参数且无返回值
 *     语法格式二；有一个参数且无返回值
 *     语法格式三：有两个以上的参数且有返回值
 *
 *  二、Lambda表达式的参数列表
 *     Lambda表达式的参数列表的数据类型可以省略不写，因为JVM可以根据代码上下文推断出参数的数据类型，即“类型推断”
 */
public class JavaLambdaBase {

    // 语法格式一
    @Test
    public void test1() {
        // 在java8之前，内部类中使用同级别的变量时，需要使用修饰符final来进行修饰，java8之后，可以不使用，但是默认仍然是final型变量，不可进行修改
        int num = 0;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World! " + num);
            }
        };
        r.run();

        System.out.println("===============");

        Runnable r1 = () -> System.out.println("Hello World! " + num);
        r1.run();
    }

    // 语法格式二
    @Test
    public void test2() {
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("Hello World!");

        System.out.println("====================");

        // 只有一个参数的Lambda表达式的参数小括号可以不写
        Consumer<String> consumer1 = x -> System.out.println(x);
        consumer1.accept("Hello World!");
    }

    // 语法格式三
    @Test
    public void test3() {
        Comparator<Integer> comparator = (x, y) -> {
          return Integer.compare(x, y);
        };
        System.out.println(comparator.compare(2,4));

        System.out.println("==========================");

        // 如果Lambda体中只有一条语句时，大括号和return语句都可以省略
        Comparator<Integer> comparator1 =(x, y) -> Integer.compare(x, y);
        System.out.println(comparator1.compare(4,2));
    }
}
