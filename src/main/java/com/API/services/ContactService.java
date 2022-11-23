package com.API.services;

import com.API.Model.Dtos.ContactDto;
import com.API.Model.Dtos.ContactMessageDto;
import com.API.Model.mappers.ContactMapper;
import com.API.Model.repositories.ContactRepository;
import com.API.exceptions.BadRequestException;
import com.API.exceptions.ContactExistingException;
import com.API.exceptions.ContactUnexistingException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ContactService
{
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public ContactService(ContactRepository contactRepository, ContactMapper contactMapper){
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    /*-Agregamos un contacto nuevo a la base de datos
      -consulta en el metodo creado en repository si el nombre ingresado existe-devolviendo un boolean
      -consulta en el metodo creado en repository si el numero ingresado existe-devolviendo un boolean
      -si existen, en cualquier if arroja una exception
      - si no existe, mapeamos el dto a entity lo guardamos en el repositorio
      -cuando ya lo guardamos, mapeamos el entity a dtoMessage y retorna contactMessageDto
    */
    public ContactMessageDto addContact(ContactDto contactDto){
        if(contactRepository.existsByName(contactDto.getName()))
            throw new ContactExistingException("el nombre:"+contactDto.getName()+" ya existe en la base de datos...");
        if(contactRepository.existsByNumber(contactDto.getNumber()))
            throw new ContactExistingException("el numero:"+contactDto.getNumber()+" ya existe en la base de datos...");
        if (contactDto.getName().equals(null) || contactDto.getNumber()<0)
            throw new BadRequestException("datos incorrectos");

        contactDto.setId(null);
        return Optional
                .ofNullable(contactDto)
                .map(dto -> contactMapper.contactDtoToEntity(dto))
                .map(entity -> contactRepository.save(entity))
                .map(entity -> contactMapper.ContactEntityToMessage(entity))
                .orElse(new ContactMessageDto());
    }

    /*  -obtemos un contacto por su ID
        -lo buscamos por la funcion  existByID.
        -cuando lo encontramos lo mapiamos convirtiendolo a a dto
     */
    public ContactDto getContact(Integer id){
        if(!contactRepository.existsById(id))
            throw new ContactUnexistingException("el contacto con codigo:"+id+" no existe en la base de datos..");
        return contactRepository.findById(id).map(contactMapper::ContactEntityToDto).orElse(null);
    }

    /*- moficamos un contacto ya guardado (cumple con el update(modifica el contacto ya creado))
      - seteamos el ID por el id mandado en la url
      -ralizamos el mismo proceso de agreagado
    */
    public ContactDto modifyContact(ContactDto contactDto,Integer id){
        if(!contactRepository.existsById(id))
            throw new ContactUnexistingException("el contacto con codigo:" + id + " no existe en la base de datos..");
        if (contactDto.getName().equals(null) || contactDto.getNumber()<0)
            throw new BadRequestException("datos incorrectos");

        contactDto.setId(id); //suponemos siempre que ese id existe
        return Optional
                .ofNullable(contactDto)
                .map(dto -> contactMapper.contactDtoToEntity(dto))
                .map(entity -> contactRepository.save(entity))
                .map(entity -> contactMapper.ContactEntityToDto(entity))
                .orElse(new ContactDto());
    }

    /* -eliminamos un contacto retornando true or false
       - si el contacto no existe en el repositorio retorna FALSE
       -si el contacto existe, utilizamos la funcion deleteById proporcionada por el repository y retornamos TRUE
    */
    public boolean deleteContact(Integer id){
        if(!contactRepository.existsById(id))
            throw new ContactUnexistingException("el contacto con codigo:" + id + " no existe en la base de datos..");
        contactRepository.deleteById(id);
        return true;
    }
}
