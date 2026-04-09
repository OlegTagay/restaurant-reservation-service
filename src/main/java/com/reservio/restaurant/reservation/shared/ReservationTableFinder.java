package com.reservio.restaurant.reservation.shared;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationTableFinder {
    private final ReservationTableRepository repository;

    public ReservationTableFinder(ReservationTableRepository repository) {
        this.repository = repository;
    }

    public ReservationTable findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ReservationTable not found with id: " + id));
    }

    public List<ReservationTable> findAll() {
        return repository.findAll();
    }
}
