package com.exam.controllers;

import com.exam.dto.EmployeeDto;
import com.exam.service.EmployeeService;
import com.exam.service.ServiceContainer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    EmployeeService employeeService = ServiceContainer.getEmployeeService();

    @GetMapping("employees/all")
    public List<EmployeeDto> findAll() {
        return employeeService.findAll();
    }

    @PostMapping("employees/create")
    public EmployeeDto create(@RequestBody EmployeeDto inputDto) {
        return employeeService.create(inputDto);
    }
}
