package com.reservio.restaurant.reservation.shared;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationTableRepository extends JpaRepository<ReservationTable, Long> {
}
