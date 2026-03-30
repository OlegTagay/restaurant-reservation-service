package com.reservio.restaurant.service;

import com.reservio.restaurant.dto.request.contactInfo.CreateContactInfoRequest;
import com.reservio.restaurant.dto.request.contactInfo.UpdateContactInfoRequest;
import com.reservio.restaurant.dto.response.contactInfo.ContactInfoResponse;
import com.reservio.restaurant.entity.ContactInfo;
import org.springframework.transaction.annotation.Transactional;

public interface IContactInfoService {
    ContactInfoResponse createContactInfo(CreateContactInfoRequest createContactInfoRequest);

    ContactInfoResponse readContactInfo(Long id);

    ContactInfoResponse updateContactInfo(Long id, UpdateContactInfoRequest updateContactInfoRequest);

    void deleteContactInfo(Long id);
}
