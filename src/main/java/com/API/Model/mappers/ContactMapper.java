package com.API.Model.mappers;

import com.API.Model.Dtos.ContactDto;
import com.API.Model.Dtos.ContactMessageDto;
import com.API.Model.Entity.ContactEntity;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ContactMapper
{
    public ContactDto ContactEntityToDto(ContactEntity contactEntity){
        return Optional.ofNullable(contactEntity)
                .map(entity ->  new ContactDto(
                                                entity.getId(),
                                                entity.getName(),
                                                entity.getNumber()))
                .orElse(new ContactDto());
    }

    public ContactEntity contactDtoToEntity(ContactDto contactDto){
        ContactEntity contactEntity =  new ContactEntity();

        contactEntity.setId(contactDto.getId());
        contactEntity.setName(contactDto.getName());
        contactEntity.setNumber(contactDto.getNumber());
        return contactEntity;
    }

    /*
        Como es solo un atributo, no utilizamos el Optional
    */
    public ContactMessageDto ContactEntityToMessage(ContactEntity contactEntity){
        ContactMessageDto contactMessage =  new ContactMessageDto();

        contactMessage.setId(contactEntity.getId());
        return contactMessage;
    }
}