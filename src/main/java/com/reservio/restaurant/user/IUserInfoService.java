package com.reservio.restaurant.user;

public interface IUserInfoService {
    UserInfoResponse createUserInfo(UserInfoRequest request);
    UserInfoResponse readUserInfo(Long id);
    UserInfoResponse updateUserInfo(Long id, UserInfoRequest request);
    void deleteUserInfo(Long id);
}
