package com.API.services;

import com.API.Model.Dtos.AddressDto;
import com.API.Model.Entity.AddressEntity;
import com.API.Model.mappers.AddressMapper;
import com.API.Model.repositories.AddressRepository;
import com.API.exceptions.BadRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<AddressEntity> findAddressById(Integer idAddress){
        if(!addressRepository.existsById(idAddress))
            throw new BadRequestException("id:"+idAddress+"no existe en la base de datos");

       AddressEntity address= addressRepository.findById(idAddress).orElse(null);
        List<AddressEntity> addressEntities = addressRepository.findAllByStreetAndNumber(address.getStreet(),address.getNumber());
        return addressEntities;
    }

    /*-recibe la direccion siempre en como formato Dto
      - mapea la direccion a entity y la guarda
      -la devuelve como entity y no como dto, porque nos hace falta para guardarla en contactEntity */
    public AddressEntity addAddress(AddressDto addressDto){
        return Optional
                .ofNullable(addressDto)
                .map(dto -> addressMapper.addressDtoToEntity(dto))
                .map(entity -> addressRepository.save(entity))
                .orElse(new AddressEntity());
    }

    public  AddressEntity modifyAddress(AddressDto addressDto,Integer id)
    {
        addressDto.setId(id); //suponemos siempre que ese id existe
        return Optional
                .ofNullable(addressDto)
                .map(dto -> addressMapper.addressDtoToEntity(dto))
                .orElse(new AddressEntity());
    }

    /*  LISTADOS POR PAGINACION */

    public List<AddressDto> getAllAddresses(Integer pageNumber, Integer pageSize, String filterFileStreet, String filterFileNumber){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<AddressDto> listAddresses = addressRepository.findAllByStreetContainsAndNumberContains(filterFileStreet,filterFileNumber,pageable)
                                                          .map(addressMapper::addressEntityToDto)
                                                          .stream()
                                                          .toList();
        return listAddresses;
    }

    public List<AddressDto> getAddressByFilterFileStreet(Integer pageNumber,Integer pageSize,String filterFileStreet) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<AddressDto> listAddresses = addressRepository.findAllByStreetContains(filterFileStreet, pageable)
                                                          .map(addressMapper::addressEntityToDto)
                                                          .stream()
                                                          .toList();
        return listAddresses;
    }

    public List<AddressDto> getAddressByFilterFileNumber(Integer pageNumber,Integer pageSize,String filterFileNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<AddressDto> listAddresses = addressRepository.findAllByNumberContains(filterFileNumber,pageable)
                                                          .map(addressMapper::addressEntityToDto)
                                                          .stream()
                                                          .toList();
        return listAddresses;
    }
}
