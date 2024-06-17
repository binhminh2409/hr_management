package com.exam.mapper.impl;

import com.exam.dto.EmployeeDto;
import com.exam.mapper.EmployeeMapper;
import com.exam.models.Employee;
import org.springframework.beans.BeanUtils;

public class EmployeeMapperImpl implements EmployeeMapper {
    @Override
    public Employee dtoToEntity(EmployeeDto dto) {
        Employee entity  = new Employee();
        BeanUtils.copyProperties(dto,entity);
        return entity;
    }

    @Override
    public EmployeeDto entityToDto(Employee entity) {
        EmployeeDto dto = new EmployeeDto();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }
}
