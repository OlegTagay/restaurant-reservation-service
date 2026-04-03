package com.reservio.restaurant.service.reservationTable;

import com.reservio.restaurant.dto.request.reservationTable.ReservationTableRequest;
import com.reservio.restaurant.dto.response.reservationTable.ReservationTableResponse;
import com.reservio.restaurant.entity.ReservationTable;
import com.reservio.restaurant.entity.UserInfo;
import com.reservio.restaurant.mapper.ReservationTableMapper;
import com.reservio.restaurant.repository.ReservationTableRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@Service
public class ReservationTableService implements IReservationTableService {
    private final ReservationTableRepository reservationTableRepository;
    private final ReservationTableMapper reservationTableMapper;

    public ReservationTableService(ReservationTableRepository reservationTableRepository, ReservationTableMapper reservationTableMapper) {
        this.reservationTableRepository = reservationTableRepository;
        this.reservationTableMapper = reservationTableMapper;
    }

    @Transactional
    @Override
    public ReservationTableResponse createReservationTable(ReservationTableRequest request) {
        log.info("Creating entity: {}", request);
        ReservationTable reservationTable = reservationTableMapper.toEntity(request);
        reservationTableRepository.save(reservationTable);

        return reservationTableMapper.toResponse(reservationTable);
    }

    @Transactional(readOnly = true)
    @Override
    public ReservationTableResponse readReservationTable(Long id) {
        log.info("Reading entity id: {}", id);
        ReservationTable reservationTable = getReservationTable(id);

        return reservationTableMapper.toResponse(reservationTable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReservationTableResponse> readReservationTables() {
        List<ReservationTable> reservationTables = getReservationTables();
        if(reservationTables.isEmpty()) {
            throw new EntityNotFoundException("ReservationTable table is empty");
        }

        return reservationTableMapper.toResponse(reservationTables);
    }

    @Transactional
    @Override
    public ReservationTableResponse updateReservationTable(Long id, ReservationTableRequest request) {
        log.info("Updating entity: {}", request);
        ReservationTable reservationTable = getReservationTable(id);

        reservationTableMapper.updateEntity(request, reservationTable);
        reservationTableRepository.save(reservationTable);

        return reservationTableMapper.toResponse(reservationTable);
    }

    @Transactional
    @Override
    public void deleteReservationTable(Long id) {
        log.info("Deleting entity id: {}", id);
        getReservationTable(id);

        reservationTableRepository.deleteById(id);
    }

    private ReservationTable getReservationTable(Long id) {
        return reservationTableRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("ReservationTable not found with id: " + id));
    }

    private List<ReservationTable> getReservationTables() {
        return reservationTableRepository.findAll();
    }
}
