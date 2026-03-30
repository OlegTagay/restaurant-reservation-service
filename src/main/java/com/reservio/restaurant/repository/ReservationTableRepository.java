package com.reservio.restaurant.repository;

import com.reservio.restaurant.entity.ReservationTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationTableRepository extends JpaRepository<ReservationTable, Long> {
}
