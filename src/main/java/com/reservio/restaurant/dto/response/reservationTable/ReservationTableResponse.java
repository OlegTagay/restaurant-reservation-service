package com.reservio.restaurant.dto.response.reservationTable;

import com.reservio.restaurant.dto.response.userInfo.UserInfoResponse;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationTableResponse(
        Integer numberOfSeats,
        Boolean isAvailable,
        LocalDate reservationDate,
        LocalTime reservationTime,
        UserInfoResponse user
) {
}
