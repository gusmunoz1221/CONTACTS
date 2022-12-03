package com.API.controllers;

import com.API.Model.Dtos.AddressDto;
import com.API.Model.Dtos.ContactDto;
import com.API.Model.Entity.AddressEntity;
import com.API.services.AddressService;
import com.API.services.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/address")
public class ContactAddress
{
    private final AddressService addressService;
    private final ContactService contactService;

    public ContactAddress(AddressService addressService, ContactService contactService) {
        this.addressService = addressService;
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAddressByPage(@RequestParam(name = "pageNumber",defaultValue = "0") Integer pageNumber,
                                                             @RequestParam(name = "pageSize",defaultValue = "2") Integer pageSize,
                                                             @RequestParam(required = false) String street,
                                                             @RequestParam(required = false)String number){
        if(street!=null && number!=null)
            return ResponseEntity.ok(addressService.getAllAddresses(pageNumber,pageSize,street,number));
          if(street!=null)
            return ResponseEntity.ok(addressService.getAddressByFilterFileStreet(pageNumber,pageSize,street));
          else return ResponseEntity.ok(addressService.getAddressByFilterFileNumber(pageNumber,pageSize,number));
    }

    @GetMapping("/{id}/contact")
    public ResponseEntity<List<ContactDto>> getContactsByIdAddress(@RequestParam(name = "pageNumber",defaultValue = "0") Integer pageNumber,
                                                                   @RequestParam(name = "pageSize",defaultValue = "2") Integer pageSize,
                                                                   @PathVariable Integer id){
        List<AddressEntity> addressEntity = addressService.findAddressById(id);
        return ResponseEntity.ok(contactService.getContactByAddress(addressEntity));
    }
}
