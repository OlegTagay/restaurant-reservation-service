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
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
            new ReservationTableRequest(numberOfSeats, LocalDate.now(), LocalTime.now(),
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
    void createReservationTable() {
        Mockito.when(mapper.toEntity(request)).thenReturn(entity);
        Mockito.when(repository.save(entity)).thenReturn(entity);
        Mockito.when(mapper.toResponse(entity)).thenReturn(expectedResponse);

        ReservationTableResponse response = service.createReservationTable(request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedResponse.id(), response.id());
        Mockito.verify(repository).save(entity);
    }

    @Test
    void readReservationTable() {
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(entity));
        Mockito.when(mapper.toResponse(entity)).thenReturn(expectedResponse);

        ReservationTableResponse response = service.readReservationTable(id);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedResponse.id(), response.id());
        Mockito.verify(repository).findById(id);
    }

    @Test
    void updateReservationTable() {
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(entity));
        Mockito.when(mapper.toResponse(entity)).thenReturn(expectedResponse);

        ReservationTableResponse response = service.updateReservationTable(id, request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedResponse.id(), response.id());
        Mockito.verify(mapper).updateEntity(request, entity);
    }

    @Test
    void deleteReservationTable() {
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(entity));

        service.deleteReservationTable(id);

        Mockito.verify(repository).deleteById(id);
    }

    @Test
    void getReservationTable_shouldThrowEntityNotFoundException_whenNotFound() {
        Long id = 99L;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.readReservationTable(id));
    }

    @Test
    void updateReservationTable_shouldThrowEntityNotFoundException_whenNotFound() {
        Long id = 99L;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.updateReservationTable(id, request));
    }

    @Test
    void deleteReservationTable_shouldThrowEntityNotFoundException_whenNotFound() {
        Long id = 99L;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.deleteReservationTable(id));
    }
}
