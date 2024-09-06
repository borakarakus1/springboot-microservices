package com.ms.organization_service.service;

import com.ms.organization_service.dto.OrganizationDto;

public interface OrganizationService {
    OrganizationDto saveOrganization(OrganizationDto organizationDto);
    OrganizationDto findByOrganizationCode(String code);
}
