package com.reservio.restaurant.booking;

import com.reservio.restaurant.contact.ContactInfoRequest;
import com.reservio.restaurant.contact.ContactInfoResponse;
import com.reservio.restaurant.contact.IContactInfoService;
import com.reservio.restaurant.exception.NoSeatsAvailableException;
import com.reservio.restaurant.reservation.userRole.IReservationTableService;
import com.reservio.restaurant.reservation.userRole.ReservationTableRequest;
import com.reservio.restaurant.reservation.userRole.ReservationTableResponse;
import com.reservio.restaurant.user.IUserInfoService;
import com.reservio.restaurant.user.UserInfoRequest;
import com.reservio.restaurant.user.UserInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Slf4j
@Service
public class BookingService implements IBookingService {
    private final IReservationTableService reservationTableService;
    private final IUserInfoService userInfoService;
    private final IContactInfoService contactInfoService;

    public BookingService(IReservationTableService reservationTableService, IUserInfoService userInfoService, IContactInfoService contactInfoService) {
        this.reservationTableService = reservationTableService;
        this.userInfoService = userInfoService;
        this.contactInfoService = contactInfoService;
    }

    @Override
    public ReservationTableResponse reserveTable(ReservationTableRequest reservationTableRequest) {
        log.info("Processing request: {}", reservationTableRequest);
        ReservationTableResponse response = findMostSuitedTable(reservationTableRequest);

        ReservationTableRequest requestWithActualSeats = new ReservationTableRequest(
                response.numberOfSeats(),  // actual table capacity, not requested
                reservationTableRequest.reservationDate(),
                reservationTableRequest.reservationTime(),
                reservationTableRequest.userInfoRequest()
        );

        return reservationTableService.updateReservationTable(response.id(), requestWithActualSeats);
    }

    @Override
    public void cancelTable(Long id) {
        reservationTableService.cancelReservation(id);
    }

    private ReservationTableResponse findMostSuitedTable(ReservationTableRequest request) {
        return reservationTableService
                .readReservationTables()
                .stream()
                .filter(response ->
                        request.numberOfSeats() <= response.numberOfSeats() &&
                                (response.reservationDate() == null
                                        || !response.reservationDate().isEqual(request.reservationDate())))
                .sorted(Comparator.comparing(ReservationTableResponse::numberOfSeats))
                .findFirst()
                .orElseThrow(() -> new NoSeatsAvailableException("No seats available!"));
    }
}
