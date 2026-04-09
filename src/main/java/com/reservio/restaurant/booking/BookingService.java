package com.reservio.restaurant.booking;

import com.reservio.restaurant.contact.ContactInfoRequest;
import com.reservio.restaurant.contact.ContactInfoResponse;
import com.reservio.restaurant.exception.NoSeatsAvailableException;
import com.reservio.restaurant.reservation.userRole.IReservationTableService;
import com.reservio.restaurant.reservation.userRole.ReservationTableRequest;
import com.reservio.restaurant.reservation.userRole.ReservationTableResponse;
import com.reservio.restaurant.user.UserInfoRequest;
import com.reservio.restaurant.user.UserInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        ReservationTableResponse response = findMostSuitedTable(reservationTableRequest);

        ReservationTableRequest request = new ReservationTableRequest(
                response.numberOfSeats(),
                reservationTableRequest.reservationDate(),
                reservationTableRequest.reservationTime(),
                reservationTableRequest.userInfoRequest()
        );

        return reservationTableService.updateReservationTable(response.id(), request);
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

    private ReservationTableResponse findMostSuitedTable(ReservationTableRequest request) {
        List<ReservationTableResponse> list = reservationTableService
                .readReservationTables()
                .stream()
                .filter(response ->
                        request.numberOfSeats() <= response.numberOfSeats() &&
                                (response.reservationDate() == null
                                || !response.reservationDate().isEqual(request.reservationDate())))
                .sorted(Comparator.comparing(ReservationTableResponse::numberOfSeats))
                .toList();

        for (ReservationTableResponse response : list) {
            return response;
        }

        throw new NoSeatsAvailableException("No seats available!");
    }
}
