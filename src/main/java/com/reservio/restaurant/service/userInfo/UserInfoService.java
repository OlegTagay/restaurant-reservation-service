package com.reservio.restaurant.service.userInfo;

import com.reservio.restaurant.dto.request.userInfo.UserInfoRequest;
import com.reservio.restaurant.dto.response.userInfo.UserInfoResponse;
import com.reservio.restaurant.entity.UserInfo;
import com.reservio.restaurant.mapper.UserInfoMapper;
import com.reservio.restaurant.repository.UserInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserInfoService implements IUserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final UserInfoMapper userInfoMapper;

    public UserInfoService(UserInfoRepository userInfoRepository, UserInfoMapper userInfoMapper) {
        this.userInfoRepository = userInfoRepository;
        this.userInfoMapper = userInfoMapper;
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
        UserInfo userInfo = getUserInfo(id);

        return userInfoMapper.toResponse(userInfo);
    }

    @Transactional
    @Override
    public UserInfoResponse updateUserInfo(Long id, UserInfoRequest request) {
        log.info("Updating entity: {}", request);
        UserInfo userInfo = getUserInfo(id);

        userInfoMapper.updateEntity(request, userInfo);
        userInfoRepository.save(userInfo);

        return userInfoMapper.toResponse(userInfo);
    }

    @Transactional
    @Override
    public void deleteUserInfo(Long id) {
        log.info("Deleting entity id: {}", id);
        getUserInfo(id);

        userInfoRepository.deleteById(id);
    }

    private UserInfo getUserInfo(Long id) {
        return userInfoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("UserInfo not found with id: " + id));
    }
}
