package com.ms.department_service.service.impl;

import com.ms.department_service.common.Mapper;
import com.ms.department_service.dto.DepartmentDto;
import com.ms.department_service.entity.Department;
import com.ms.department_service.repository.DepartmentRepository;
import com.ms.department_service.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final Mapper mapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = mapper.toEntity(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        DepartmentDto dto  = mapper.toDto(savedDepartment);

        return dto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);

        DepartmentDto departmentDto = mapper.toDto(department);
        return departmentDto;
    }
}
