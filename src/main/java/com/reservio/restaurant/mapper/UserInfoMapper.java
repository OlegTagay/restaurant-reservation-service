package com.reservio.restaurant.mapper;

import com.reservio.restaurant.dto.request.userInfo.UserInfoRequest;
import com.reservio.restaurant.dto.response.userInfo.UserInfoResponse;
import com.reservio.restaurant.entity.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ContactInfoMapper.class)
public interface UserInfoMapper {
    @Mapping(target = "contactInfoResponse", source = "contactInfo")
    UserInfoResponse toResponse(UserInfo entity);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contactInfo", source = "contactInfoRequest")
    UserInfo toEntity(UserInfoRequest request);
}
