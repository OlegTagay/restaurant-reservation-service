package com.reservio.restaurant.service;

import com.reservio.restaurant.dto.request.reservationTable.ReservationTableRequest;
import com.reservio.restaurant.dto.response.reservationTable.ReservationTableResponse;
import com.reservio.restaurant.repository.ContactInfoRepository;
import com.reservio.restaurant.repository.ReservationTableRepository;
import com.reservio.restaurant.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationTableService implements IReservationTableService {
    private ReservationTableRepository reservationTableRepository;
    private IUserInfoService userInfoService;
    private IContactInfoService contactInfoService;

    public ReservationTableService(ReservationTableRepository reservationTableRepository, IUserInfoService userInfoService, IContactInfoService contactInfoService) {
        this.reservationTableRepository = reservationTableRepository;
        this.userInfoService = userInfoService;
        this.contactInfoService = contactInfoService;
    }

    @Override
    public ReservationTableResponse createTable(ReservationTableRequest request) {
        return null;
    }

    @Override
    public ReservationTableResponse readTable(Long id) {
        return null;
    }

    @Override
    public ReservationTableResponse updateTable(Long id, ReservationTableRequest request) {
        return null;
    }

    @Override
    public void deleteTable(Long id) {
    }
}
