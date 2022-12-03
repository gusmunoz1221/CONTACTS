package com.API.services;

import com.API.Model.Dtos.ContactDto;
import com.API.Model.Dtos.ContactMessageDto;
import com.API.Model.Entity.AddressEntity;
import com.API.Model.mappers.AddressMapper;
import com.API.Model.mappers.ContactMapper;
import com.API.Model.repositories.AddressRepository;
import com.API.Model.repositories.ContactRepository;
import com.API.exceptions.BadRequestException;
import com.API.exceptions.ContactExistingException;
import com.API.exceptions.ContactUnexistingException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    private final ContactRepository contactRepository;
    private final AddressRepository addressRepository;
    private  final AddressService addressService;
    private final ContactMapper contactMapper;
    private final AddressMapper addressMapper;

    public ContactService(ContactRepository contactRepository, AddressRepository addressRepository, AddressService addressService, ContactMapper contactMapper, AddressMapper addressMapper) {
        this.contactRepository = contactRepository;
        this.addressRepository = addressRepository;
        this.addressService = addressService;
        this.contactMapper = contactMapper;
        this.addressMapper = addressMapper;
    }

    /*-Agregamos un contacto nuevo a la base de datos
      -consulta en el metodo creado en repository si el nombre ingresado existe-devolviendo un boolean
      -consulta en el metodo creado en repository si el numero ingresado existe-devolviendo un boolean
      -si existen, en cualquier if arroja una exception
      - si no existe, mapeamos el dto a entity lo guardamos en el repositorio
      -cuando ya lo guardamos, mapeamos el entity a dtoMessage y retorna contactMessageDto */
    public ContactMessageDto addContact(ContactDto contactDto){
        if(contactRepository.existsByName(contactDto.getName()))
            throw new ContactExistingException("el nombre:" + contactDto.getName() + " ya existe en la base de datos...");
        if(contactRepository.existsByPhone(contactDto.getPhone()))
            throw new ContactExistingException("el numero:" + contactDto.getPhone() + " ya existe en la base de datos...");
        if (contactDto.getName().equals(null) || contactDto.getPhone()==null)
            throw new BadRequestException("datos incorrectos");

        AddressEntity addressEntity = addressService.addAddress(contactDto.getAddress());
        return Optional
                .ofNullable(contactDto)
                .map(dto -> contactMapper.contactDtoToEntity(dto,addressEntity))
                .map(entity -> contactRepository.save(entity))
                .map(entity -> contactMapper.ContactEntityToMessage(entity))
                .orElse(new ContactMessageDto());
    }

    /*  -obtemos un contacto por su ID
        -lo buscamos por la funcion  existByID.
        -cuando lo encontramos lo mapiamos convirtiendolo a a dto*/
    public ContactDto getContact(Integer id) {
        if (!contactRepository.existsById(id))
            throw new ContactUnexistingException("el contacto con codigo:" + id + " no existe en la base de datos..");
        return contactRepository.findById(id).map(contactMapper::ContactEntityToDto).orElse(null);
    }

    /*- moficamos un contacto ya guardado (cumple con el update(modifica el contacto ya creado))
      - seteamos el ID por el id mandado en la url
      -ralizamos el mismo proceso de agreagado*/
    public ContactDto modifyContact(ContactDto contactDto, Integer id, AddressEntity addressEntity) {
        if (!contactRepository.existsById(id))
            throw new ContactUnexistingException("el contacto con codigo:" + id + " no existe en la base de datos..");
        if (contactDto.getName().equals(null) || contactDto.getPhone().equals(null))
            throw new BadRequestException("datos incorrectos");

        contactDto.setId(id); //suponemos siempre que ese id existe
        return Optional
                .ofNullable(contactDto)
                .map(dto -> contactMapper.contactDtoToEntity(dto, addressEntity))
                .map(entity -> contactRepository.save(entity))
                .map(entity -> contactMapper.ContactEntityToDto(entity))
                .orElse(new ContactDto());
    }

    /* -eliminamos un contacto retornando true or false
       - si el contacto no existe en el repositorio retorna FALSE
       -si el contacto existe, utilizamos la funcion deleteById proporcionada por el repository y retornamos TRUE*/
    public boolean deleteContact(Integer id) {
        if (!contactRepository.existsById(id))
            throw new ContactUnexistingException("el contacto con codigo:" + id + " no existe en la base de datos..");
        contactRepository.deleteById(id);
        return true;
    }

    /*  LISTADOS POR PAGINACION */
    public List<ContactDto> getAllContacts(Integer pageNumber, Integer pageSize, String name, String phone){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<ContactDto> listContacts = contactRepository.findAllByNameContainsAndPhoneContains(name,phone,pageable)
                                                         .map(contactMapper::ContactEntityToDto)
                                                         .stream()
                                                         .toList();
        return listContacts;
    }

    public List<ContactDto> getContactsByName(Integer pageNumber, Integer pageSize,String name){
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        List<ContactDto> listContacts = contactRepository.findAllByNameContains(name,pageable)
                                                         .map(contactMapper::ContactEntityToDto)
                                                         .stream()
                                                         .toList();
       return listContacts;

    }

        public List<ContactDto> getContactsByPhone(Integer pageNumber, Integer pageSize,String phone){
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
            List<ContactDto> listContacts = contactRepository.findAllByPhoneContains(phone,pageable)
                                                             .map(contactMapper::ContactEntityToDto)
                                                             .stream().toList();
        return listContacts;
    }

    /*recibo por parametro una lista de direcciones
    *creo una nueva lista donde se van a guardar todos los contactos
    * dentro del bucle,añado a la lista los contactos cuyo ids son los que tienen la misma calle y numero*/
    public List<ContactDto> getContactByAddress(List<AddressEntity> addressEntityList){
        List<ContactDto> listContacts = new ArrayList<>();

        for(AddressEntity address :  addressEntityList)
            listContacts.add(contactRepository.findById(address.getIdaddress())
                        .map(contactMapper::ContactEntityToDto)
                        .orElse(null));
        return listContacts;
    }
}

