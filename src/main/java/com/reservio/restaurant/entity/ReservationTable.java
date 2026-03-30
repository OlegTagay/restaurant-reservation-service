package com.reservio.restaurant.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
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
    private UserInfo user;
}
