package com.reservio.restaurant.dto.request.reservationTable;

import com.reservio.restaurant.dto.request.userInfo.UserInfoRequest;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationTableRequest(
        Integer numberOfSeats,
        @FutureOrPresent
        LocalDate reservationDate,
        LocalTime reservationTime,
        @NotNull
        UserInfoRequest userInfoRequest
) {
}
