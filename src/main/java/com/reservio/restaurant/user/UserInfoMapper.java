package com.reservio.restaurant.user;

import com.reservio.restaurant.contact.ContactInfoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = ContactInfoMapper.class)
public interface UserInfoMapper {
    @Mapping(target = "contactInfoResponse", source = "contactInfo")
    UserInfoResponse toResponse(UserInfo entity);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contactInfo", source = "contactInfoRequest")
    UserInfo toEntity(UserInfoRequest request);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contactInfo", source = "contactInfoRequest")
    void updateEntity(UserInfoRequest request, @MappingTarget UserInfo entity);
}
