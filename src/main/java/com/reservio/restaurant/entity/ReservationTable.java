package com.reservio.restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ReservationTable {
    @Id
    @GeneratedValue
    private Long id;
    private Integer numberOfSeats;
    private Boolean isAvailable;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;
}
