package com.reservio.restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserInfo {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_info_id")
    private ContactInfo contactInfo;
}
