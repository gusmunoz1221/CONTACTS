package com.API.Model.mappers;

import com.API.Model.Dtos.AddressDto;
import com.API.Model.Dtos.AddressResponseDto;
import com.API.Model.Entity.AddressEntity;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class AddressMapper
{
    public AddressDto addressEntityToDto(AddressEntity addressEntity){
        return Optional.ofNullable(addressEntity)
                .map(entity ->  new AddressDto(
                                                entity.getStreet(),
                                                entity.getNumber()
                                                )
                    )
                .orElse(new AddressDto());
    }

    public AddressResponseDto addressEntityToDtoResponse(AddressEntity addressEntity){
        return Optional.ofNullable(addressEntity)
                .map(entity ->  new AddressResponseDto(
                                                        entity.getIdaddress(),
                                                        entity.getStreet(),
                                                        entity.getNumber()
                                                        )
                    )
                .orElse(new AddressResponseDto());
    }

    public AddressEntity addressDtoToEntity(AddressDto addressDto){
        AddressEntity addressEntity =  new AddressEntity();

        addressEntity.setStreet(addressDto.getStreet());
        addressEntity.setNumber(addressDto.getNumber());
        return addressEntity;
    }
}