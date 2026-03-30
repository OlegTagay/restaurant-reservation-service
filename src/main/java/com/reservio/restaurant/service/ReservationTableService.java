package com.reservio.restaurant.service;

import com.reservio.restaurant.repository.ContactInfoRepository;
import com.reservio.restaurant.repository.ReservationTableRepository;
import com.reservio.restaurant.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationTableService {
    private ReservationTableRepository reservationTableRepository;
    private  UserInfoRepository userInfoRepository;


}
