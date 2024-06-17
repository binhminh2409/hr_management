package com.exam.mapper;

import com.exam.dto.EmployeeDto;
import com.exam.models.Employee;
import org.springframework.beans.BeanUtils;

public interface EmployeeMapper {
    Employee dtoToEntity(EmployeeDto dto);
    EmployeeDto entityToDto(Employee entity);
}
