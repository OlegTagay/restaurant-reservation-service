package com.reservio.restaurant;

import com.reservio.restaurant.contact.ContactInfoRequest;
import com.reservio.restaurant.contact.ContactInfoResponse;
import com.reservio.restaurant.contact.ContactInfo;
import com.reservio.restaurant.contact.ContactInfoMapper;
import com.reservio.restaurant.contact.ContactInfoRepository;
import com.reservio.restaurant.contact.ContactInfoService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ContactInfoServiceTests {
    @Mock
    ContactInfoRepository repository;
    @Mock
    ContactInfoMapper mapper;
    @InjectMocks
    ContactInfoService service;

    private final Long id = 1L;
    private final ContactInfo entity = new ContactInfo(1L, "123456789", "address");
    private final ContactInfoRequest request = new ContactInfoRequest("123456789", "address");
    private final ContactInfoResponse expectedResponse = new ContactInfoResponse(1L, "123456789", "address");

    @Test
    void createContactInfo() {
        Mockito.when(mapper.toEntity(request)).thenReturn(entity);
        Mockito.when(repository.save(entity)).thenReturn(entity);
        Mockito.when(mapper.toResponse(entity)).thenReturn(expectedResponse);

        ContactInfoResponse response = service.createContactInfo(request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedResponse.id(), response.id());
        Mockito.verify(repository).save(entity);
    }

    @Test
    void readContactInfo() {
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(entity));
        Mockito.when(mapper.toResponse(entity)).thenReturn(expectedResponse);

        ContactInfoResponse response = service.readContactInfo(id);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedResponse.id(), response.id());
        Mockito.verify(repository).findById(id);
    }

    @Test
    void updateContactInfo() {
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(entity));
        Mockito.when(repository.save(entity)).thenReturn(entity);
        Mockito.when(mapper.toResponse(entity)).thenReturn(expectedResponse);

        ContactInfoResponse response = service.updateContactInfo(id, request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedResponse.id(), response.id());
        Mockito.verify(mapper).updateEntity(request, entity);
        Mockito.verify(repository).save(entity);
    }

    @Test
    void deleteContactInfo() {
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(entity));

        service.deleteContactInfo(id);

        Mockito.verify(repository).deleteById(id);
    }

    @Test
    void getContactInfo_shouldThrowEntityNotFoundException_whenNotFound() {
        Long id = 99L;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.readContactInfo(id));
    }

    @Test
    void updateContactInfo_shouldThrowEntityNotFoundException_whenNotFound() {
        Long id = 99L;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.updateContactInfo(id, request));
    }

    @Test
    void deleteContactInfo_shouldThrowEntityNotFoundException_whenNotFound() {
        Long id = 99L;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.deleteContactInfo(id));
    }
}
