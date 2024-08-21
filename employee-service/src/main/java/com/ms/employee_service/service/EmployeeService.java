package com.ms.employee_service.service;

import com.ms.employee_service.dto.APIResponseDto;
import com.ms.employee_service.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployeeById(Long id);
}
