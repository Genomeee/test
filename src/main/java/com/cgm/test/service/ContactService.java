package com.cgm.test.service;

import com.cgm.test.dto.ContactDTO;

import java.util.List;

public interface ContactService {
    List<ContactDTO> findAllByTenantIdAndName(String tenantId, String name);

}
