package com.ms.organization_service.common;

import com.ms.organization_service.dto.OrganizationDto;
import com.ms.organization_service.entity.Organization;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public static OrganizationDto toDto(Organization organization) {
        if (organization == null) {
            return null;
        }
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setId(organization.getId());
        organizationDto.setOrganizationCode(organization.getOrganizationCode());
        organizationDto.setOrganizationName(organization.getOrganizationName());
        organizationDto.setOrganizationDescription(organization.getOrganizationDescription());
        organizationDto.setCreatedDate(organization.getCreatedDate());
        return organizationDto;
    }

    public static Organization toEntity(OrganizationDto organizationDto) {
        if (organizationDto == null) {
            return null;
        }
        Organization organization = new Organization();
        organization.setId(organizationDto.getId());
        organization.setOrganizationCode(organizationDto.getOrganizationCode());
        organization.setOrganizationName(organizationDto.getOrganizationName());
        organization.setOrganizationDescription(organizationDto.getOrganizationDescription());
        organization.setCreatedDate(organizationDto.getCreatedDate());
        return organization;
    }

}
