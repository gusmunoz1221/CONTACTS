package com.API.controllers;

import com.API.Model.Dtos.ContactDto;
import com.API.Model.Dtos.ContactMessageDto;
import com.API.Model.Entity.AddressEntity;
import com.API.services.AddressService;
import com.API.services.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(contactService.addContact(contactDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> getContact(@PathVariable(name = "id") int id){
        return ResponseEntity.ok(contactService.getContact(id));
    }

    @GetMapping
    public ResponseEntity<List<ContactDto>> getContactsByPage(@RequestParam(name = "pageNumber",defaultValue = "0") Integer pageNumber,
                                                                    @RequestParam(name = "pageSize",defaultValue = "1") Integer pageSize,
                                                                    @RequestParam(required = false) String name,
                                                                    @RequestParam(required = false)String phone){
        if(name!=null && phone!=null)
            return ResponseEntity.ok(contactService.getAllContacts(pageNumber,pageSize,name,phone));
        if(name!=null)
            return ResponseEntity.ok(contactService.getContactsByName(pageNumber,pageSize,name));
        else return ResponseEntity.ok(contactService.getContactsByPhone(pageNumber,pageSize,phone));
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


