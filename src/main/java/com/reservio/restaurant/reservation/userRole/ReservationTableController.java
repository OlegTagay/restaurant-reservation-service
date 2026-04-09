package com.reservio.restaurant.reservation.userRole;

import com.reservio.restaurant.booking.IBookingService;
import com.reservio.restaurant.exception.NoSeatsAvailableException;
import com.reservio.restaurant.reservation.shared.ReservationTableMapper;
import com.reservio.restaurant.user.UserInfoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/v1/reservation", produces = "application/json")
public class ReservationTableController {
    private final IBookingService bookingService;

    public ReservationTableController(IBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping(path = "/{id}")
    public ReservationTableResponse readTable(@PathVariable Long id) {
        log.info("Reading request id: {}", id);
        return bookingService.readTable(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationTableResponse reserveTable(@Valid @RequestBody ReservationTableRequest reservationTableRequest) {
        log.info("Processing request: {}", reservationTableRequest);
        return bookingService.reserveTable(reservationTableRequest);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public ReservationTableResponse updateTable(@PathVariable Long id, @Valid @RequestBody ReservationTableRequest request) {
        log.info("Updating request id: {}, with request: {}", id, request);
        return bookingService.updateTable(id, request);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelTable(@PathVariable Long id) {
        log.info("Cancel table id: {}", id);
        bookingService.cancelTable(id);
    }
}
