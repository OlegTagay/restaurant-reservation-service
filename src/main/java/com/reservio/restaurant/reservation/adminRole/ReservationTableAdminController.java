package com.reservio.restaurant.reservation.adminRole;

import com.reservio.restaurant.reservation.userRole.ReservationTableRequest;
import com.reservio.restaurant.reservation.userRole.ReservationTableResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/v1/admin/reservation", produces = "application/json")
public class ReservationTableAdminController {
    private final IReservationTableAdminService reservationTableAdminService;

    public ReservationTableAdminController(IReservationTableAdminService reservationTableAdminService) {
        this.reservationTableAdminService = reservationTableAdminService;
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTable(@PathVariable Long id) {
        log.info("Deleting id: {}", id);
        reservationTableAdminService.deleteReservationTable(id);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ReservationTableResponse updateTable(@PathVariable Long id, @Valid @RequestBody ReservationTableRequest request) {
        log.info("Updating request id: {}, with request: {}", id, request);
//        return reservationTableAdminService.update(id, request);
        return null;
    }
}
