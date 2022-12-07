package com.API.Model.mappers;

import com.API.Model.Dtos.ContactDto;
import com.API.Model.Dtos.ContactMessageDto;
import com.API.Model.Dtos.ContactResponseDto;
import com.API.Model.Entity.AddressEntity;
import com.API.Model.Entity.ContactEntity;
import com.API.Model.repositories.AddressRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ContactMapper
{
   private final AddressMapper addressMapper;
   private final AddressRepository addressRepository;
    public ContactMapper(AddressMapper addressMapper, AddressRepository addressRepository) {
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
    }

    public ContactDto ContactEntityToDto(ContactEntity contactEntity){
        return Optional.ofNullable(contactEntity)
                .map(entity ->  new ContactDto(
                                                entity.getName(),
                                                entity.getPhone(),
                                                addressRepository.findById(entity.getId())
                                                                 .map(addressMapper::addressEntityToDto)
                                                                 .orElse(null)
                                                ))
                .orElse(new ContactDto());
    }
    public ContactResponseDto ContactEntityToDtoResponse(ContactEntity contactEntity){
        return Optional.ofNullable(contactEntity)
                .map(entity ->  new ContactResponseDto(
                                                        entity.getId(),
                                                        entity.getName(),
                                                        entity.getPhone(),
                                                        addressRepository.findById(entity
                                                                         .getId())
                                                                         .map(addressMapper::addressEntityToDtoResponse)
                                                                         .orElse(null)
                ))
                .orElse(new ContactResponseDto());
    }

    public ContactEntity contactDtoToEntity(ContactDto contactDto,AddressEntity address){
        ContactEntity contactEntity =  new ContactEntity();

        contactEntity.setName(contactDto.getName());
        contactEntity.setPhone(contactDto.getPhone());
        contactEntity.setAddress(address);
        return contactEntity;
    }

    public ContactEntity contactDtoToEntityModify(ContactDto contactDto,AddressEntity address,Integer id){
        ContactEntity contactEntity =  new ContactEntity();

        contactEntity.setId((id));
        contactEntity.setName(contactDto.getName());
        contactEntity.setPhone(contactDto.getPhone());
        contactEntity.setAddress(address);
        return contactEntity;
    }

    public ContactMessageDto ContactEntityToMessage(ContactEntity contactEntity){
        ContactMessageDto contactMessage =  new ContactMessageDto();

        contactMessage.setId(contactEntity.getId());
        return contactMessage;
    }
}