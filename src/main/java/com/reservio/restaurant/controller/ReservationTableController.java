package com.reservio.restaurant.controller;

import com.reservio.restaurant.dto.request.reservationTable.ReservationTableRequest;
import com.reservio.restaurant.dto.request.userInfo.UserInfoRequest;
import com.reservio.restaurant.dto.response.reservationTable.ReservationTableResponse;
import com.reservio.restaurant.mapper.ReservationTableMapper;
import com.reservio.restaurant.service.reservationTable.ReservationTableService;
import com.reservio.restaurant.service.userInfo.UserInfoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/v1/reservation", produces = "application/json")
public class ReservationTableController {
    private final ReservationTableService reservationTableService;
    private final ReservationTableMapper reservationTableMapper;
    private final UserInfoService userInfoService;

    public ReservationTableController(ReservationTableService reservationTableService, ReservationTableMapper reservationTableMapper, UserInfoService userInfoService) {
        this.reservationTableService = reservationTableService;
        this.reservationTableMapper = reservationTableMapper;
        this.userInfoService = userInfoService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationTableResponse reserveTable(@Valid @RequestBody ReservationTableRequest reservationTableRequest) {
        log.info("Processing request: {}", reservationTableRequest);
        ReservationTableResponse response =
                reservationTableService
                        .readReservationTables()
                        .stream()
                        .filter(reservationTableResponse -> reservationTableResponse.isAvailable()
                                && reservationTableRequest.numberOfSeats() <= reservationTableResponse.numberOfSeats()
                                && reservationTableRequest.numberOfSeats() >= reservationTableResponse.numberOfSeats()
                                && (reservationTableResponse.reservationDate() == null)
                                && (reservationTableResponse.reservationTime() == null))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("No seats available!"));

        ReservationTableResponse finalResponse = reservationTableService.createReservationTable(reservationTableMapper.toRequest(response));

        return finalResponse;
    }
}
