package com.reservio.restaurant.repository;

import com.reservio.restaurant.entity.ContactInfo;
import com.reservio.restaurant.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {
}
