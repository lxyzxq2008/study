package com.sunroom.lambda;

import com.sunroom.beans.Employee;

public class FilterEmployeeBySalary implements MyPredicate <Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() >= 5000;
    }
}
