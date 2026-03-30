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
        @Min(value = 2, message = "Minimum seat number is 2")
        @Max(value = 26, message = "Maximum seat number is 25")
        Integer numberOfSeats,
        Boolean isAvailable,
        @FutureOrPresent
        LocalDate reservationDate,
        @FutureOrPresent
        LocalTime reservationTime,
        @NotNull
        UserInfoRequest user
) {
}
