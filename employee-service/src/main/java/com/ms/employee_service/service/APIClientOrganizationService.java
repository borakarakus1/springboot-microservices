package com.ms.employee_service.service;

import com.ms.employee_service.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "organization-service")
public interface APIClientOrganizationService {
    @GetMapping("/api/organization/{code}")
    OrganizationDto getOrganization(@PathVariable("code") String organizationCode);
}
