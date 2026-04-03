package com.reservio.restaurant.service.reservationTable;

import com.reservio.restaurant.dto.request.reservationTable.ReservationTableRequest;
import com.reservio.restaurant.dto.response.reservationTable.ReservationTableResponse;

import java.util.List;

public interface IReservationTableService {
    ReservationTableResponse createReservationTable(ReservationTableRequest request);
    ReservationTableResponse readReservationTable(Long id);
    List<ReservationTableResponse> readReservationTables();
    ReservationTableResponse updateReservationTable(Long id, ReservationTableRequest request);
    void deleteReservationTable(Long id);
}
