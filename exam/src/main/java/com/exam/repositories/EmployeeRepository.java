package com.exam.repositories;

import com.exam.models.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAll();
    Employee create(Employee employee);

    Employee findByEmail(String email);

    Employee findById(Long id);
}
