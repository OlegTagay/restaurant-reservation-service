package com.reservio.restaurant.reservation.userRole;

import com.reservio.restaurant.reservation.shared.ReservationTable;
import com.reservio.restaurant.reservation.shared.ReservationTableFinder;
import com.reservio.restaurant.reservation.shared.ReservationTableMapper;
import com.reservio.restaurant.reservation.shared.ReservationTableRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ReservationTableService implements IReservationTableService {
    private final ReservationTableRepository reservationTableRepository;
    private final ReservationTableMapper reservationTableMapper;
    private final ReservationTableFinder reservationTableFinder;

    public ReservationTableService(ReservationTableRepository reservationTableRepository, ReservationTableMapper reservationTableMapper, ReservationTableFinder reservationTableFinder) {
        this.reservationTableRepository = reservationTableRepository;
        this.reservationTableMapper = reservationTableMapper;
        this.reservationTableFinder = reservationTableFinder;
    }

    @Transactional(readOnly = true)
    @Override
    public ReservationTableResponse readReservationTable(Long id) {
        log.info("Reading entity id: {}", id);
        ReservationTable reservationTable = reservationTableFinder.findById(id);

        return reservationTableMapper.toResponse(reservationTable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReservationTableResponse> readReservationTables() {
        List<ReservationTable> reservationTables = reservationTableFinder.findAll();
        if (reservationTables.isEmpty()) {
            throw new EntityNotFoundException("ReservationTable table is empty");
        }

        return reservationTableMapper.toResponse(reservationTables);
    }

    @Transactional
    @Override
    public ReservationTableResponse updateReservationTable(Long id, ReservationTableRequest request) {
        log.info("Updating entity: {}", request);
        ReservationTable reservationTable = reservationTableFinder.findById(id);

        reservationTableMapper.updateEntity(request, reservationTable);

        return reservationTableMapper.toResponse(reservationTable);
    }

    @Transactional
    @Override
    public void cancelReservation(Long id) {
        ReservationTable table = reservationTableFinder.findById(id);
        table.setReservationDate(null);
        table.setReservationTime(null);
        table.setUserInfo(null);
    }
}
