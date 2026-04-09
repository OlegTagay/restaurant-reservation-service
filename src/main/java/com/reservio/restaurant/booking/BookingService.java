package com.reservio.restaurant.booking;

import com.reservio.restaurant.contact.ContactInfoRequest;
import com.reservio.restaurant.contact.ContactInfoResponse;
import com.reservio.restaurant.exception.NoSeatsAvailableException;
import com.reservio.restaurant.reservation.shared.ReservationTableMapper;
import com.reservio.restaurant.reservation.userRole.IReservationTableService;
import com.reservio.restaurant.reservation.userRole.ReservationTableRequest;
import com.reservio.restaurant.reservation.userRole.ReservationTableResponse;
import com.reservio.restaurant.reservation.userRole.ReservationTableService;
import com.reservio.restaurant.user.IUserInfoService;
import com.reservio.restaurant.user.UserInfoRequest;
import com.reservio.restaurant.user.UserInfoResponse;
import com.reservio.restaurant.user.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookingService implements IBookingService {
    private final IReservationTableService reservationTableService;

    public BookingService(IReservationTableService reservationTableService) {
        this.reservationTableService = reservationTableService;
    }

    @Override
    public ReservationTableResponse readTable(Long id) {
        return reservationTableService.readReservationTable(id);
    }

    @Override
    public ReservationTableResponse reserveTable(ReservationTableRequest reservationTableRequest) {
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
                        .orElseThrow(() -> new NoSeatsAvailableException("No seats available!"));

        ReservationTableResponse finalResponse =
                reservationTableService.updateReservationTable(response.id(), reservationTableRequest);

        return finalResponse;
    }

    @Override
    public ReservationTableResponse updateTable(Long id, ReservationTableRequest request) {
        return reservationTableService.updateReservationTable(id, request);
    }

    @Override
    public UserInfoResponse updateUserInfo(Long id, UserInfoRequest request) {
        return null;
    }

    @Override
    public ContactInfoResponse updateContactInfo(Long id, ContactInfoRequest request) {
        return null;
    }

    @Override
    public void cancelTable(Long id) {
        reservationTableService.cancelReservation(id);
    }
}
