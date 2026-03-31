package com.reservio.restaurant;

import com.reservio.restaurant.dto.request.contactInfo.ContactInfoRequest;
import com.reservio.restaurant.dto.response.contactInfo.ContactInfoResponse;
import com.reservio.restaurant.entity.ContactInfo;
import com.reservio.restaurant.mapper.ContactInfoMapper;
import com.reservio.restaurant.repository.ContactInfoRepository;
import com.reservio.restaurant.service.contactInfo.ContactInfoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ContactInfoServiceTests {
    @Mock
    ContactInfoRepository contactInfoRepository;
    @Mock
    ContactInfoMapper contactInfoMapper;
    @InjectMocks
    ContactInfoService contactInfoService;

    private final Long id = 1L;
    private final ContactInfo contactInfo = new ContactInfo(1L, "123456789", "address");
    private final ContactInfoRequest request = new ContactInfoRequest("123456789", "address");
    private final ContactInfoResponse expectedResponse = new ContactInfoResponse(1L, "123456789", "address");

    @Test
    void createContactInfo() {
        Mockito.when(contactInfoMapper.toEntity(request)).thenReturn(contactInfo);
        Mockito.when(contactInfoRepository.save(contactInfo)).thenReturn(contactInfo);
        Mockito.when(contactInfoMapper.toResponse(contactInfo)).thenReturn(expectedResponse);

        ContactInfoResponse response = contactInfoService.createContactInfo(request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedResponse.id(), response.id());
        Mockito.verify(contactInfoRepository).save(contactInfo);
    }

    @Test
    void readContactInfo() {
        Mockito.when(contactInfoRepository.findById(id)).thenReturn(Optional.of(contactInfo));
        Mockito.when(contactInfoMapper.toResponse(contactInfo)).thenReturn(expectedResponse);

        ContactInfoResponse response = contactInfoService.readContactInfo(id);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedResponse.id(), response.id());
        Mockito.verify(contactInfoRepository).findById(id);
    }

    @Test
    void updateContactInfo() {
        Mockito.when(contactInfoRepository.findById(id)).thenReturn(Optional.of(contactInfo));
        Mockito.when(contactInfoRepository.save(contactInfo)).thenReturn(contactInfo);
        Mockito.when(contactInfoMapper.toResponse(contactInfo)).thenReturn(expectedResponse);

        ContactInfoResponse response = contactInfoService.updateContactInfo(id, request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedResponse.id(), response.id());
        Mockito.verify(contactInfoRepository).save(contactInfo);
    }

    @Test
    void deleteContactInfo() {
        Mockito.when(contactInfoRepository.findById(id)).thenReturn(Optional.of(contactInfo));

        contactInfoService.deleteContactInfo(id);

        Mockito.verify(contactInfoRepository).deleteById(id);
    }
}
