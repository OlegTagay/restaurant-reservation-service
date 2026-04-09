package com.reservio.restaurant.reservation.shared;

import com.reservio.restaurant.reservation.userRole.ReservationTableRequest;
import com.reservio.restaurant.reservation.userRole.ReservationTableResponse;
import com.reservio.restaurant.user.UserInfoMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserInfoMapper.class)
public interface ReservationTableMapper {
    @Mapping(target = "userInfoResponse", source = "userInfo")
    ReservationTableResponse toResponse(ReservationTable entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userInfo", source = "userInfoRequest")
    ReservationTable toEntity(ReservationTableRequest request);

    @Mapping(target = "userInfo", source = "userInfoResponse")
    ReservationTable toEntity(ReservationTableResponse response);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userInfo", source = "userInfoRequest")
    void updateEntity(ReservationTableRequest request, @MappingTarget ReservationTable entity);

    @Mapping(target = "userInfoResponse", source = "userInfo")
    List<ReservationTableResponse> toResponse(List<ReservationTable> entities);

    @Mapping(target = "userInfoRequest", source = "userInfoResponse")
    ReservationTableRequest toRequest(ReservationTableResponse response);
}
