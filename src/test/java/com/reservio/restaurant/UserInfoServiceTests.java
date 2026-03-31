package com.reservio.restaurant;

import com.reservio.restaurant.dto.request.contactInfo.ContactInfoRequest;
import com.reservio.restaurant.dto.request.userInfo.UserInfoRequest;
import com.reservio.restaurant.dto.response.contactInfo.ContactInfoResponse;
import com.reservio.restaurant.dto.response.userInfo.UserInfoResponse;
import com.reservio.restaurant.entity.ContactInfo;
import com.reservio.restaurant.entity.UserInfo;
import com.reservio.restaurant.mapper.UserInfoMapper;
import com.reservio.restaurant.repository.UserInfoRepository;
import com.reservio.restaurant.service.UserInfoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserInfoServiceTests {
    @Mock
    UserInfoRepository repository;
    @Mock
    UserInfoMapper mapper;
    @InjectMocks
    UserInfoService service;

    private final Long id = 1L;
    private final UserInfoRequest request = new UserInfoRequest("John", "Doe",
            new ContactInfoRequest("123456789", "address"));
    private final UserInfo entity = new UserInfo(id, "John", "Doe",
            new ContactInfo(id, "123456789", "address"));
    private final UserInfoResponse expectedResponse = new UserInfoResponse(id, "John", "Doe",
            new ContactInfoResponse(id, "123456789", "address"));

    @Test
    void createUserInfo() {
        Mockito.when(mapper.toEntity(request)).thenReturn(entity);
        Mockito.when(repository.save(entity)).thenReturn(entity);
        Mockito.when(mapper.toResponse(entity)).thenReturn(expectedResponse);

        UserInfoResponse response = service.createUserInfo(request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedResponse.id(), response.id());
        Mockito.verify(repository).save(entity);
    }

    @Test
    void readUserInfo() {
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(entity));
        Mockito.when(mapper.toResponse(entity)).thenReturn(expectedResponse);

        UserInfoResponse response = service.readUserInfo(id);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedResponse.id(), response.id());
        Mockito.verify(repository).findById(id);
    }

    @Test
    void updateContactInfo() {
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(entity));
        Mockito.when(repository.save(entity)).thenReturn(entity);
        Mockito.when(mapper.toResponse(entity)).thenReturn(expectedResponse);

        UserInfoResponse response = service.updateUserInfo(id, request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedResponse.id(), response.id());
        Mockito.verify(repository).save(entity);
    }

    @Test
    void deleteContactInfo() {
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(entity));

        service.deleteUserInfo(id);

        Mockito.verify(repository).deleteById(id);
    }
}
