package com.sunroom.lambda;

import com.sunroom.beans.Employee;

public class FilterEmployeeByAge implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return Integer.compare(employee.getAge(), 30) > 0;
    }
}
