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

@Slf4j
@Service
public class ReservationTableService implements IReservationTableService {
    private final ReservationTableRepository reservationTableRepository;
    private final ReservationTableMapper reservationTableMapper;

    public ReservationTableService(ReservationTableRepository reservationTableRepository, ReservationTableMapper reservationTableMapper) {
        this.reservationTableRepository = reservationTableRepository;
        this.reservationTableMapper = reservationTableMapper;
    }

    @Override
    public ReservationTableResponse createTable(ReservationTableRequest request) {
        log.info("Creating entity: {}", request);
        ReservationTable reservationTable = reservationTableMapper.toEntity(request);
        reservationTableRepository.save(reservationTable);

        return reservationTableMapper.toResponse(reservationTable);
    }

    @Override
    public ReservationTableResponse readTable(Long id) {
        log.info("Reading entity id: {}", id);
        ReservationTable reservationTable = getReservationTable(id);

        return reservationTableMapper.toResponse(reservationTable);
    }

    @Override
    public ReservationTableResponse updateTable(Long id, ReservationTableRequest request) {
        log.info("Updating entity: {}", request);
        ReservationTable reservationTable = getReservationTable(id);

        reservationTableMapper.updateEntity(request, reservationTable);
        reservationTableRepository.save(reservationTable);

        return reservationTableMapper.toResponse(reservationTable);
    }

    @Override
    public void deleteTable(Long id) {
        log.info("Deleting entity id: {}", id);
        getReservationTable(id);

        reservationTableRepository.deleteById(id);
    }

    private ReservationTable getReservationTable(Long id) {
        return reservationTableRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("ReservationTable not found with id: " + id));
    }
}
