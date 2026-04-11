package com.reservio.restaurant.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserInfoService implements IUserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final UserInfoMapper userInfoMapper;
    private final UserInfoFinder userInfoFinder;

    public UserInfoService(UserInfoRepository userInfoRepository, UserInfoMapper userInfoMapper, UserInfoFinder userInfoFinder) {
        this.userInfoRepository = userInfoRepository;
        this.userInfoMapper = userInfoMapper;
        this.userInfoFinder = userInfoFinder;
    }

    @Transactional
    @Override
    public UserInfoResponse createUserInfo(UserInfoRequest request) {
        log.info("Creating entity: {}", request);
        UserInfo userInfo = userInfoMapper.toEntity(request);
        userInfoRepository.save(userInfo);

        return userInfoMapper.toResponse(userInfo);
    }

    @Transactional(readOnly = true)
    @Override
    public UserInfoResponse readUserInfo(Long id) {
        log.info("Reading entity id: {}", id);
        UserInfo userInfo = userInfoFinder.getUserInfo(id);

        return userInfoMapper.toResponse(userInfo);
    }

    @Transactional
    @Override
    public UserInfoResponse updateUserInfo(Long id, UserInfoRequest request) {
        log.info("Updating entity: {}", request);
        UserInfo userInfo = userInfoFinder.getUserInfo(id);

        userInfoMapper.updateEntity(request, userInfo);

        return userInfoMapper.toResponse(userInfo);
    }

    @Transactional
    @Override
    public void deleteUserInfo(Long id) {
        log.info("Deleting entity id: {}", id);
        userInfoFinder.getUserInfo(id);
        userInfoRepository.deleteById(id);
    }
}
