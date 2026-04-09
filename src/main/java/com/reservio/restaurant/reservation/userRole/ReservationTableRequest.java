package com.reservio.restaurant.reservation.userRole;

import com.reservio.restaurant.user.UserInfoRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationTableRequest(
        Integer numberOfSeats,
        @FutureOrPresent
        LocalDate reservationDate,
        LocalTime reservationTime,
        @Valid
        @NotNull
        UserInfoRequest userInfoRequest
) {
}
