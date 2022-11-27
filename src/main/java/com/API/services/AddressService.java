package com.API.services;

import com.API.Model.Dtos.AddressDto;
import com.API.Model.Entity.AddressEntity;
import com.API.Model.mappers.AddressMapper;
import com.API.Model.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService
{
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    public AddressService(AddressMapper addressMapper, AddressRepository addressRepository) {
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
    }

    /*-recibe la direccion siempre en como formato Dto
      - mapea la direccion a entity y la guarda
      -la devuelve como entity y no como dto, porque nos hace falta para guardarla en */
    public AddressEntity addAddress(AddressDto addressDto){
        return Optional
                .ofNullable(addressDto)
                .map(dto -> addressMapper.addressDtoToEntity(dto))
                .map(entity -> addressRepository.save(entity))
                .orElse(new AddressEntity());
    }
}
