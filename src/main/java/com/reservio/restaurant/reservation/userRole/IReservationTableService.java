package com.reservio.restaurant.reservation.userRole;

import java.util.List;

public interface IReservationTableService {
    ReservationTableResponse readReservationTable(Long id);

    List<ReservationTableResponse> readReservationTables();

    ReservationTableResponse updateReservationTable(Long id, ReservationTableRequest request);

    void cancelReservation(Long id);
}
