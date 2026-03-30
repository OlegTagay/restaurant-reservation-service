package com.reservio.restaurant.dto.response.reservationTable;

import com.reservio.restaurant.dto.response.userInfo.UserInfoResponse;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservationTableResponse {
    private Integer numberOfSeats;
    private Boolean isAvailable;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private UserInfoResponse user;
}
