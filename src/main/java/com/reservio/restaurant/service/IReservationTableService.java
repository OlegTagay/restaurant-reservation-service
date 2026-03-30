package com.reservio.restaurant.service;

import com.reservio.restaurant.dto.request.reservationTable.ReservationTableRequest;
import com.reservio.restaurant.dto.response.reservationTable.ReservationTableResponse;

public interface IReservationTableService {
    ReservationTableResponse createTable(ReservationTableRequest request);
    ReservationTableResponse readTable(Long id);
    ReservationTableResponse updateTable(Long id, ReservationTableRequest request);
    ReservationTableResponse deleteTable(Long id);
}
