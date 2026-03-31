package com.reservio.restaurant.service;

import com.reservio.restaurant.dto.request.userInfo.UserInfoRequest;
import com.reservio.restaurant.dto.response.userInfo.UserInfoResponse;
import com.reservio.restaurant.entity.UserInfo;
import com.reservio.restaurant.mapper.UserInfoMapper;
import com.reservio.restaurant.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserInfoService implements IUserInfoService {
    private final IContactInfoService contactInfoService;
    private final UserInfoRepository userInfoRepository;
    private final UserInfoMapper userInfoMapper;

    public UserInfoService(IContactInfoService contactInfoService, UserInfoRepository userInfoRepository, UserInfoMapper userInfoMapper) {
        this.contactInfoService = contactInfoService;
        this.userInfoRepository = userInfoRepository;
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public UserInfoResponse createUserInfo(UserInfoRequest request) {
        log.info("Creating entity: {}", request);
        UserInfo userInfo = userInfoMapper.toEntity(request);
        userInfoRepository.save(userInfo);

        return userInfoMapper.toResponse(userInfo);
    }

    @Override
    public UserInfoResponse readTable(Long id) {
        return null;
    }

    @Override
    public UserInfoResponse updateTable(Long id, UserInfoRequest request) {
        return null;
    }

    @Override
    public UserInfoResponse deleteTable(Long id) {
        return null;
    }
}
