package com.reservio.restaurant.booking;

import com.reservio.restaurant.contact.ContactInfoRequest;
import com.reservio.restaurant.contact.ContactInfoResponse;
import com.reservio.restaurant.reservation.userRole.ReservationTableRequest;
import com.reservio.restaurant.reservation.userRole.ReservationTableResponse;
import com.reservio.restaurant.user.UserInfoRequest;
import com.reservio.restaurant.user.UserInfoResponse;

public interface IBookingService {
    ReservationTableResponse reserveTable(ReservationTableRequest reservationTableRequest);

    void cancelTable(Long id);
}
