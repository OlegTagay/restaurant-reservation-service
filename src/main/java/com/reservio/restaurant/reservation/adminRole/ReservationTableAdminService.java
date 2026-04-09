package com.reservio.restaurant.reservation.adminRole;

import com.reservio.restaurant.reservation.shared.ReservationTable;
import com.reservio.restaurant.reservation.shared.ReservationTableFinder;
import com.reservio.restaurant.reservation.shared.ReservationTableMapper;
import com.reservio.restaurant.reservation.shared.ReservationTableRepository;
import com.reservio.restaurant.reservation.userRole.ReservationTableRequest;
import com.reservio.restaurant.reservation.userRole.ReservationTableResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ReservationTableAdminService implements IReservationTableAdminService {
    private final ReservationTableRepository reservationTableRepository;
    private final ReservationTableMapper reservationTableMapper;
    private final ReservationTableFinder reservationTableFinder;


    public ReservationTableAdminService(ReservationTableRepository reservationTableRepository, ReservationTableMapper reservationTableMapper, ReservationTableFinder reservationTableFinder) {
        this.reservationTableRepository = reservationTableRepository;
        this.reservationTableMapper = reservationTableMapper;
        this.reservationTableFinder = reservationTableFinder;
    }

    @Transactional
    @Override
    public ReservationTableResponse createReservationTable(ReservationTableRequest request) {
        log.info("Creating entity: {}", request);
        ReservationTable reservationTable = reservationTableMapper.toEntity(request);
        reservationTableRepository.save(reservationTable);

        return reservationTableMapper.toResponse(reservationTable);
    }

    @Transactional
    @Override
    public void deleteReservationTable(Long id) {
        log.info("Deleting entity id: {}", id);
        reservationTableFinder.findById(id);

        reservationTableRepository.deleteById(id);
    }
}
