package com.reservio.restaurant.service.contactInfo;

import com.reservio.restaurant.dto.request.contactInfo.ContactInfoRequest;
import com.reservio.restaurant.dto.response.contactInfo.ContactInfoResponse;

public interface IContactInfoService {
    ContactInfoResponse createContactInfo(ContactInfoRequest request);
    ContactInfoResponse readContactInfo(Long id);
    ContactInfoResponse updateContactInfo(Long id, ContactInfoRequest request);
    void deleteContactInfo(Long id);
}
