package com.API.controllers;

import com.API.Model.Dtos.ContactDto;
import com.API.Model.Dtos.ContactMessageDto;
import com.API.Model.Entity.AddressEntity;
import com.API.services.AddressService;
import com.API.services.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController
{
    private final ContactService contactService;
    private final AddressService addressService;

    public ContactController(ContactService contactService, AddressService addressService){
        this.contactService = contactService;
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<ContactMessageDto> AddContact(@RequestBody @Validated ContactDto contactDto){
        AddressEntity address = addressService.addAddress(contactDto.getAddress());
        return ResponseEntity.ok(contactService.addContact(contactDto,address));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> getContact(@PathVariable(name = "id") int id){
        return ResponseEntity.ok(contactService.getContact(id));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<ContactDto> modifyContact(@RequestBody ContactDto contactDto,@PathVariable(name = "id") Integer id){
        AddressEntity address = addressService.modifyAddress(contactDto.getAddress(),id);
        return ResponseEntity.ok(contactService.modifyContact(contactDto,id,address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> DeleteContact(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(contactService.deleteContact(id));
    }

}


