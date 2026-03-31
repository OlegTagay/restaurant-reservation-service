package com.reservio.restaurant;

import com.reservio.restaurant.dto.request.contactInfo.ContactInfoRequest;
import com.reservio.restaurant.dto.request.reservationTable.ReservationTableRequest;
import com.reservio.restaurant.dto.request.userInfo.UserInfoRequest;
import com.reservio.restaurant.dto.response.contactInfo.ContactInfoResponse;
import com.reservio.restaurant.dto.response.reservationTable.ReservationTableResponse;
import com.reservio.restaurant.dto.response.userInfo.UserInfoResponse;
import com.reservio.restaurant.entity.ContactInfo;
import com.reservio.restaurant.entity.ReservationTable;
import com.reservio.restaurant.entity.UserInfo;
import com.reservio.restaurant.mapper.ReservationTableMapper;
import com.reservio.restaurant.mapper.UserInfoMapper;
import com.reservio.restaurant.repository.ReservationTableRepository;
import com.reservio.restaurant.repository.UserInfoRepository;
import com.reservio.restaurant.service.reservationTable.ReservationTableService;
import com.reservio.restaurant.service.userInfo.UserInfoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ReservationTableServiceTests {
    @Mock
    ReservationTableRepository repository;
    @Mock
    ReservationTableMapper mapper;
    @InjectMocks
    ReservationTableService service;

    private final Long id = 1L;
    private final Integer numberOfSeats = 2;
    private final ReservationTableRequest request =
            new ReservationTableRequest(numberOfSeats, false, LocalDate.now(), LocalTime.now(),
                    new UserInfoRequest("John", "Doe",
                            new ContactInfoRequest("123456789", "address")));
    private final ReservationTable entity =
            new ReservationTable(id, numberOfSeats, false, LocalDate.now(), LocalTime.now(),
                    new UserInfo(id, "John", "Doe",
                            new ContactInfo(id, "123456789", "address")));
    private final ReservationTableResponse expectedResponse =
            new ReservationTableResponse(id, numberOfSeats, false, LocalDate.now(), LocalTime.now(),
                    new UserInfoResponse(id, "John", "Doe",
                            new ContactInfoResponse(id, "123456789", "address")));

    @Test
    void createUserInfo() {
        Mockito.when(mapper.toEntity(request)).thenReturn(entity);
        Mockito.when(repository.save(entity)).thenReturn(entity);
        Mockito.when(mapper.toResponse(entity)).thenReturn(expectedResponse);

        ReservationTableResponse response = service.createTable(request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedResponse.id(), response.id());
        Mockito.verify(repository).save(entity);
    }

    @Test
    void readUserInfo() {
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(entity));
        Mockito.when(mapper.toResponse(entity)).thenReturn(expectedResponse);

        ReservationTableResponse response = service.readTable(id);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedResponse.id(), response.id());
        Mockito.verify(repository).findById(id);
    }

    @Test
    void updateContactInfo() {
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(entity));
        Mockito.when(repository.save(entity)).thenReturn(entity);
        Mockito.when(mapper.toResponse(entity)).thenReturn(expectedResponse);

        ReservationTableResponse response = service.updateTable(id, request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedResponse.id(), response.id());
        Mockito.verify(repository).save(entity);
    }

    @Test
    void deleteContactInfo() {
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(entity));

        service.deleteTable(id);

        Mockito.verify(repository).deleteById(id);
    }
}
