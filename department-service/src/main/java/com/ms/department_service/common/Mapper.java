package com.ms.department_service.common;

import com.ms.department_service.dto.DepartmentDto;
import com.ms.department_service.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public static DepartmentDto toDto(Department department) {
        if (department == null) {
            return null;
        }
        DepartmentDto dto = new DepartmentDto();
        dto.setId(department.getId());
        dto.setDepartmentName(department.getDepartmentName());
        dto.setDepartmentCode(department.getDepartmentCode());
        dto.setDepartmentDescription(department.getDepartmentDescription());
        return dto;
    }

    public static Department toEntity(DepartmentDto departmentDto) {
        if (departmentDto == null) {
            return null;
        }
        Department department = new Department();
        department.setId(departmentDto.getId());
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentCode(departmentDto.getDepartmentCode());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        return department;
    }
}
