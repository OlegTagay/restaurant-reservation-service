package com.reservio.restaurant.reservation.adminRole;

import com.reservio.restaurant.reservation.userRole.ReservationTableRequest;
import com.reservio.restaurant.reservation.userRole.ReservationTableResponse;

public interface IReservationTableAdminService {
    ReservationTableResponse createReservationTable(ReservationTableRequest request);
    void deleteReservationTable(Long id);
}
