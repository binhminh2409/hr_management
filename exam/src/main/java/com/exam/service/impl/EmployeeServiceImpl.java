package com.exam.service.impl;

import com.exam.dto.EmployeeDto;
import com.exam.mapper.EmployeeMapper;
import com.exam.mapper.impl.EmployeeMapperImpl;
import com.exam.models.Employee;
import com.exam.repositories.EmployeeRepository;
import com.exam.repositories.impl.EmployeeRepositoryImpl;
import com.exam.service.EmployeeService;
import com.exam.service.ServiceContainer;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();

    EmployeeMapper employeeMapper = new EmployeeMapperImpl();


    public static void main(String[] args) {
        EmployeeService es = ServiceContainer.getEmployeeService();
        System.out.println(es.findAll());
    }

    @Override
    public List<EmployeeDto> findAll() {
        List<Employee> entities = employeeRepository.findAll();
        List<EmployeeDto> dtos = new ArrayList<EmployeeDto>();
        for (Employee entity : entities){
            EmployeeDto dto = employeeMapper.entityToDto(entity);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public EmployeeDto create(EmployeeDto inputDto) {
        Employee inputEntity = employeeMapper.dtoToEntity(inputDto);
        Employee resultEntity = employeeRepository.create(inputEntity);
        EmployeeDto resultDto = employeeMapper.entityToDto(resultEntity);
        return resultDto;
    }
}
