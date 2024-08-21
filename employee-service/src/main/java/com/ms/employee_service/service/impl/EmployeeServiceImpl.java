package com.ms.employee_service.service.impl;

import com.ms.employee_service.common.Mapper;
import com.ms.employee_service.dto.APIResponseDto;
import com.ms.employee_service.dto.DepartmentDto;
import com.ms.employee_service.dto.EmployeeDto;
import com.ms.employee_service.entity.Employee;
import com.ms.employee_service.repository.EmployeeRepository;
import com.ms.employee_service.service.APIClient;
import com.ms.employee_service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Mapper mapper;
    private final APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = mapper.toEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = mapper.toDto(savedEmployee);

        return savedEmployeeDto;
    }

    @Override
    public APIResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
        APIResponseDto apiResponseDto = new APIResponseDto();
        EmployeeDto employeeDto = mapper.toDto(employee);
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }

}

