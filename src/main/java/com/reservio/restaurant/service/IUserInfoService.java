package com.reservio.restaurant.service;

import com.reservio.restaurant.dto.request.reservationTable.ReservationTableRequest;
import com.reservio.restaurant.dto.request.userInfo.UserInfoRequest;
import com.reservio.restaurant.dto.response.reservationTable.ReservationTableResponse;
import com.reservio.restaurant.dto.response.userInfo.UserInfoResponse;

public interface IUserInfoService {
    UserInfoResponse createUserInfo(UserInfoRequest request);
    UserInfoResponse readUserInfo(Long id);
    UserInfoResponse updateUserInfo(Long id, UserInfoRequest request);
    void deleteUserInfo(Long id);
}
