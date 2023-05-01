package com.cgm.test.service;

import com.cgm.test.dto.ContactDTO;
import com.cgm.test.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<ContactDTO> findAllByTenantIdAndName(String tenantId, String name) {
        return contactRepository.findAllByTenantIdAndName(tenantId, name)
                .stream()
                .map(contact -> ContactDTO.builder()
                        .matrixId(contact.getMatrixId())
                        .name(contact.getName())
                        .build())
                .toList();
    }
}
