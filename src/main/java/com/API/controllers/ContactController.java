package com.API.controllers;

import com.API.Model.Dtos.ContactDto;
import com.API.Model.Dtos.ContactMessageDto;
import com.API.services.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController
{
    private final ContactService contactService;

    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<ContactMessageDto> AddContact(@RequestBody @Validated ContactDto contactDto){
        return ResponseEntity.ok(contactService.addContact(contactDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> getContact(@PathVariable(name = "id") int id){
        return ResponseEntity.ok(contactService.getContact(id));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<ContactDto> deleteContact(@RequestBody ContactDto contactDto,@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(contactService.modifyContact(contactDto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> DeleteContact(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(contactService.deleteContact(id));
    }

}


