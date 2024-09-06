package com.ms.organization_service.service.impl;

import com.ms.organization_service.common.Mapper;
import com.ms.organization_service.dto.OrganizationDto;
import com.ms.organization_service.entity.Organization;
import com.ms.organization_service.repository.OrganizationRepository;
import com.ms.organization_service.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final Mapper mapper;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = mapper.toEntity(organizationDto);
        Organization savedOrganization = organizationRepository.save(organization);

        OrganizationDto savedOrganizationDto = mapper.toDto(savedOrganization);

        return savedOrganizationDto;
    }

    @Override
    public OrganizationDto findByOrganizationCode(String code) {
        Organization organization = organizationRepository.findByOrganizationCode(code).orElseThrow();
        OrganizationDto organizationDto = mapper.toDto(organization);
        return organizationDto;
    }
}
