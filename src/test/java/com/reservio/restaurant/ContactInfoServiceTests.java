package com.reservio.restaurant;

import com.reservio.restaurant.dto.request.contactInfo.ContactInfoRequest;
import com.reservio.restaurant.dto.response.contactInfo.ContactInfoResponse;
import com.reservio.restaurant.entity.ContactInfo;
import com.reservio.restaurant.mapper.ContactInfoMapper;
import com.reservio.restaurant.repository.ContactInfoRepository;
import com.reservio.restaurant.service.ContactInfoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ContactInfoServiceTests {
    @Mock
    ContactInfoRepository contactInfoRepository;
    @Mock
    ContactInfoMapper contactInfoMapper;
    @InjectMocks
    ContactInfoService contactInfoService;

    @Test
    void createContactInfo() {
        ContactInfoRequest request = new ContactInfoRequest("123456789", "address");
        ContactInfo entity = new ContactInfo();
        ContactInfoResponse expectedResponse = new ContactInfoResponse(1L, "123456789", "address");

        Mockito.when(contactInfoMapper.toEntity(request)).thenReturn(entity);
        Mockito.when(contactInfoRepository.save(entity)).thenReturn(entity);
        Mockito.when(contactInfoMapper.toResponse(entity)).thenReturn(expectedResponse);

        ContactInfoResponse response = contactInfoService.createContactInfo(request);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.id());
        Assertions.assertEquals(request.address(), response.address());
        Assertions.assertEquals(request.phoneNumber(), response.phoneNumber());
        Mockito.verify(contactInfoRepository).save(entity);
    }
}
