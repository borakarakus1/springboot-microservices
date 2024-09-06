package com.ms.employee_service.service.impl;

import com.ms.employee_service.common.Mapper;
import com.ms.employee_service.dto.APIResponseDto;
import com.ms.employee_service.dto.DepartmentDto;
import com.ms.employee_service.dto.EmployeeDto;
import com.ms.employee_service.dto.OrganizationDto;
import com.ms.employee_service.entity.Employee;
import com.ms.employee_service.repository.EmployeeRepository;
import com.ms.employee_service.service.APIClientDepartmentService;
import com.ms.employee_service.service.APIClientOrganizationService;
import com.ms.employee_service.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Mapper mapper;
    private final APIClientDepartmentService apiClientDep;
    private final APIClientOrganizationService apiClientOrg;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = mapper.toEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = mapper.toDto(savedEmployee);

        return savedEmployeeDto;
    }

    @Override
    @CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment") // Retry pattern
    public APIResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }

        OrganizationDto organizationDto = apiClientOrg.getOrganization(employee.getOrganizationCode());
        DepartmentDto departmentDto = apiClientDep.getDepartment(employee.getDepartmentCode());

        APIResponseDto apiResponseDto = new APIResponseDto();
        EmployeeDto employeeDto = mapper.toDto(employee);
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);

        return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long id,Exception exception) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development Department");

        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }

        APIResponseDto apiResponseDto = new APIResponseDto();
        EmployeeDto employeeDto = mapper.toDto(employee);
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }

}

