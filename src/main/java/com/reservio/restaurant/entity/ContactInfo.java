package com.reservio.restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ContactInfo {
    @Id
    @GeneratedValue
    private Long id;
    private String phoneNumber;
    private String address;
}
