package com.reservio.restaurant.mapper;

import com.reservio.restaurant.dto.request.contactInfo.ContactInfoRequest;
import com.reservio.restaurant.dto.response.contactInfo.ContactInfoResponse;
import com.reservio.restaurant.entity.ContactInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContactInfoMapper {
    ContactInfoResponse toResponse(ContactInfo entity);
    @Mapping(target = "id", ignore = true)
    ContactInfo toEntity(ContactInfoRequest request);
}