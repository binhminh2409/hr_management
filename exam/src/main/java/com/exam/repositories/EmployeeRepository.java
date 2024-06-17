package com.exam.repositories;

import com.exam.models.Employee;

public interface EmployeeRepository {
    Employee findAll();
    Employee create(Employee employee);
}
