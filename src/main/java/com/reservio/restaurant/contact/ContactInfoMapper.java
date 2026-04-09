package com.reservio.restaurant.contact;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContactInfoMapper {
    ContactInfoResponse toResponse(ContactInfo entity);
    @Mapping(target = "id", ignore = true)
    ContactInfo toEntity(ContactInfoRequest request);
    @Mapping(target = "id", ignore = true)
    void updateEntity(ContactInfoRequest request, @MappingTarget ContactInfo entity);
}