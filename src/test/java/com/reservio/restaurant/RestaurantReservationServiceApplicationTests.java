package com.reservio.restaurant;

import com.reservio.restaurant.entity.ContactInfo;
import com.reservio.restaurant.repository.ContactInfoRepository;
import com.reservio.restaurant.repository.ReservationTableRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestaurantReservationServiceApplicationTests {

	@Autowired
	ContactInfoRepository contactInfoRepository;

	@Test
	void contextLoads() {
		System.out.println(contactInfoRepository.findAll());
	}

}
