package com.reservio.restaurant.contact;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ContactInfoService implements IContactInfoService {
    private final ContactInfoRepository contactInfoRepository;
    private final ContactInfoMapper contactInfoMapper;

    public ContactInfoService(ContactInfoRepository contactInfoRepository, ContactInfoMapper contactInfoMapper) {
        this.contactInfoRepository = contactInfoRepository;
        this.contactInfoMapper = contactInfoMapper;
    }

    @Transactional
    public ContactInfoResponse createContactInfo(ContactInfoRequest request) {
        log.info("Creating entity: {}", request);
        ContactInfo contactInfo = contactInfoMapper.toEntity(request);
        contactInfoRepository.save(contactInfo);

        return contactInfoMapper.toResponse(contactInfo);
    }

    @Transactional(readOnly = true)
    public ContactInfoResponse readContactInfo(Long id) {
        log.info("Reading entity id: {}", id);

        return contactInfoMapper.toResponse(getContactInfo(id));
    }

    @Transactional
    public ContactInfoResponse updateContactInfo(Long id, ContactInfoRequest request) {
        log.info("Updating entity: {}", request);
        ContactInfo contactInfo = getContactInfo(id);

        contactInfoMapper.updateEntity(request, contactInfo);
        contactInfoRepository.save(contactInfo);

        return contactInfoMapper.toResponse(contactInfo);
    }

    @Transactional
    public void deleteContactInfo(Long id) {
        log.info("Deleting entity id: {}", id);
        getContactInfo(id);

        contactInfoRepository.deleteById(id);
    }

    private ContactInfo getContactInfo(Long id) {
        return contactInfoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("ContactInfo not found with id: " + id));
    }
}
