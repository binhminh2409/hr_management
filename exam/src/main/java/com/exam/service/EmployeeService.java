package com.exam.service;

import com.exam.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAll();

    EmployeeDto create(EmployeeDto inputDto);
}
