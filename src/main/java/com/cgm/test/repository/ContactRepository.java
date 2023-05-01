package com.cgm.test.repository;

import com.cgm.test.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
    List<Contact> findAllByTenantIdAndName(String tenantId, String name);
}
