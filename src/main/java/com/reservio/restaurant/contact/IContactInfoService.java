package com.reservio.restaurant.contact;

public interface IContactInfoService {
    ContactInfoResponse createContactInfo(ContactInfoRequest request);
    ContactInfoResponse readContactInfo(Long id);
    ContactInfoResponse updateContactInfo(Long id, ContactInfoRequest request);
    void deleteContactInfo(Long id);
}
