package com.API.services;

import com.API.Model.Dtos.AddressResponseDto;
import com.API.Model.Entity.AddressEntity;
import com.API.Model.mappers.AddressMapper;
import com.API.Model.repositories.AddressRepository;
import com.API.exceptions.BadRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AddressService
{
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    public AddressService(AddressMapper addressMapper, AddressRepository addressRepository) {
        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
    }


    public  AddressEntity findAddressById(Integer idAddress){
        if(!addressRepository.existsById(idAddress))
            throw new BadRequestException("id:"+idAddress+"no existe en la base de datos");

        return addressRepository.findById(idAddress).orElse(null);
    }

    /*  LISTADOS POR PAGINACION */

    public List<AddressResponseDto> getAllAddresses(Integer pageNumber, Integer pageSize, String filterFileStreet, String filterFileNumber){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<AddressResponseDto> listAddresses = addressRepository.findAllByStreetContainsAndNumberContains(filterFileStreet,filterFileNumber,pageable)
                                                                  .map(addressMapper::addressEntityToDtoResponse)
                                                                  .stream()
                                                                  .toList();
        return listAddresses;
    }

    public List<AddressResponseDto> getAddressByFilterFileStreet(Integer pageNumber,Integer pageSize,String filterFileStreet) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<AddressResponseDto> listAddresses = addressRepository.findAllByStreetContains(filterFileStreet, pageable)
                                                                  .map(addressMapper::addressEntityToDtoResponse)
                                                                  .stream()
                                                                  .toList();
        return listAddresses;
    }

    public List<AddressResponseDto> getAddressByFilterFileNumber(Integer pageNumber,Integer pageSize,String filterFileNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<AddressResponseDto> listAddresses = addressRepository.findAllByNumberContains(filterFileNumber,pageable)
                                                                  .map(addressMapper::addressEntityToDtoResponse)
                                                                  .stream()
                                                                  .toList();
        return listAddresses;
    }
}
