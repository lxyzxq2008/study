package com.sunroom.lambda;

import com.sunroom.beans.Employee;

/**
 * @package: com.sunroom.lambda.FilterEmployeeByAge
 * @description: 年龄大于30岁的比较器
 * @author sunroom
 * @date 2023/1/18 7:42 上午
 * @version 1.0
 */
public class FilterEmployeeByAge implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return Integer.compare(employee.getAge(), 30) > 0;
    }
}
