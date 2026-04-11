package com.reservio.restaurant.contact;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ContactInfoFinder {

    ContactInfoRepository contactInfoRepository;

    public ContactInfoFinder(ContactInfoRepository contactInfoRepository) {
        this.contactInfoRepository = contactInfoRepository;
    }

    public ContactInfo getContactInfo(Long id) {
        return contactInfoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("ContactInfo not found with id: " + id));
    }
}
