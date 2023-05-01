package com.cgm.test.controller;

import com.cgm.test.dto.ContactDTO;
import com.cgm.test.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping(value = "/contactController")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(value = "/{name}")
    @ResponseBody
    public ResponseEntity<List<ContactDTO>> searchForContacts(@PathVariable("name") String name,
                                                              @RequestHeader("X_TENANT_ID") String tenantId) {
        if (name == null || tenantId == null) {
            throw new WebApplicationException("name and tenantId are required", Response.Status.BAD_REQUEST);
        }
        var response = contactService.findAllByTenantIdAndName(tenantId, name);
        var status = response.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(response, status);
    }
}
