package com.reservio.restaurant.reservation.userRole;

import com.reservio.restaurant.user.UserInfoResponse;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationTableResponse(
        Long id,
        Integer numberOfSeats,
        Boolean isAvailable,
        LocalDate reservationDate,
        LocalTime reservationTime,
        UserInfoResponse userInfoResponse
) {
}
