package com.reservio.restaurant.user;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoFinder {
    private final UserInfoRepository userInfoRepository;

    public UserInfoFinder(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public UserInfo getUserInfo(Long id) {
        return userInfoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("UserInfo not found with id: " + id));
    }
}
