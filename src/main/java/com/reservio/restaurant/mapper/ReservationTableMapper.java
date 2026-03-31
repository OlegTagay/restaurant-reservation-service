package com.reservio.restaurant.mapper;

import com.reservio.restaurant.dto.request.reservationTable.ReservationTableRequest;
import com.reservio.restaurant.dto.response.reservationTable.ReservationTableResponse;
import com.reservio.restaurant.entity.ReservationTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = UserInfoMapper.class)
public interface ReservationTableMapper {
    @Mapping(target = "userInfoResponse", source = "userInfo")
    ReservationTableResponse toResponse(ReservationTable entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userInfo", source = "userInfoRequest")
    ReservationTable toEntity(ReservationTableRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userInfo", source = "userInfoRequest")
    void updateEntity(ReservationTableRequest request, @MappingTarget ReservationTable entity);
}
