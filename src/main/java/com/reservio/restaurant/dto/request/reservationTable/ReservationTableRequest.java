package com.reservio.restaurant.dto.request.reservationTable;

import com.reservio.restaurant.dto.request.userInfo.UserInfoRequest;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservationTableRequest {
    @Min(value = 2, message = "Minimum seat number is 2")
    @Max(value = 26, message = "Maximum seat number is 25")
    private Integer numberOfSeats;
    private Boolean isAvailable;
    @FutureOrPresent
    private LocalDate reservationDate;
    @FutureOrPresent
    private LocalTime reservationTime;
    @NotNull
    private UserInfoRequest user;
}
