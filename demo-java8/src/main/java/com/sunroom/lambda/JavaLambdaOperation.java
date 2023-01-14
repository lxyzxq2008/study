package com.sunroom.lambda;

import com.sunroom.beans.Employee;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

public class JavaLambdaOperation {

//    public static void main(String[] args) {
//        JavaLambdaOperation.noLambda();
//        JavaLambdaOperation.useLambda();
//    }

    @Test
    public void noLambda() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        System.out.println("未使用Lambda表达式  "+comparator.compare(1,2));
    }

    /**
     * 使用Lambda表达式
     */
    @Test
    public void useLambda() {
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x, y);
        System.out.println("使用Lambda表达式  "+comparator.compare(2,1));
    }

    List<Employee> emps = Arrays.asList(
            new Employee("张三", 20, 3459.23),
            new Employee("李四", 30, 4459.23),
            new Employee("王五", 33, 3659.23),
            new Employee("赵六", 25, 3859.23),
            new Employee("孙七", 40, 6459.23),
            new Employee("周八", 34, 5459.23)
    );
    // 需求：获取公司员工中，薪资大于5000的员工
    // 原来写法：
    @Test
    public void filterEmployee() {
        List<Employee> employees = new ArrayList<>();
        for (Employee emp : emps) {
            if (emp.getSalary() >= 5000) {
                employees.add(emp);
            }
        }

        System.out.println(Arrays.toString(employees.toArray()));
    }

    // 优化方式一：
    // 使用策略设计模式，定义接口MyPredicate，创建接口实现类来判定对应条件
    public List<Employee> filterEmployee(List<Employee> emps, MyPredicate<Employee> predicate) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : emps) {
            if (predicate.test(emp)) {
                result.add(emp);
            }
        }

        return result;
    }
}
