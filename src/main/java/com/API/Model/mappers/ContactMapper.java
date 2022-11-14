package com.API.Model.mappers;

import com.API.Model.Entity.ContactEntity;
import com.API.Model.dtos.ContactDto;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ContactMapper
{
    public ContactDto ContactEntityToDto(ContactEntity contactEntity)
    {
        return Optional.ofNullable(contactEntity)
                .map(entity ->  new ContactDto(
                        entity.getId(),
                        entity.getName(),
                        entity.getNumber()))
                .orElse(new ContactDto());
    }

    public ContactEntity contactDtoToEntity(ContactDto contactDto)
    {
        ContactEntity contactEntity =  new ContactEntity();

        contactEntity.setId(contactDto.getId());
        contactEntity.setName(contactDto.getName());
        contactEntity.setNumber(contactDto.getNumber());
        return contactEntity;
    }
}