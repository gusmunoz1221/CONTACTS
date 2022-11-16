package com.API.services;

import com.API.Model.Entity.ContactEntity;
import com.API.Model.dtos.ContactDto;
import com.API.Model.dtos.ContactMessageDto;
import com.API.Model.mappers.ContactMapper;
import com.API.Model.repositories.ContactRepository;
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
      -si ingresa un id por error lo setea a null siempre.
      -optional es un objeto,el cual si sale bien devuelvo lo esperado ,si no un opcional
      -una vez que mapeamos el dto a entity lo guardamos en el repositorio
      -cuando ya lo guardamos, mapeamos el entity a dtoMessage y retorna contactMessageDto
    */
    public ContactMessageDto addContact(ContactDto contactDto){
        contactDto.setId(null);
        return Optional
                .ofNullable(contactDto)
                .map(dto -> contactMapper.contactDtoToEntity(dto))
                .map(entity -> contactRepository.save(entity))
                .map(entity -> contactMapper.ContactEntityToMessage(entity))
                .orElse(new ContactMessageDto());
    }

    /*  -obtemos un contacto por su ID
        -lo buscamos por la funcion  buscar mediante ID.
        -  lo buscamos en el repositorio- (recordemos que el repositorio es de tipo entity)
        -cuando lo encontramos lo mapiamos convirtiendolo a a dto
     */
    public ContactDto getContact(Integer id){
        return contactRepository.findById(id).map(contactMapper::ContactEntityToDto).orElse(null);
    }


    /*- moficamos un contacto ya guardado (cumple con el update(modifica el contacto ya creado))
      - seteamos el ID por el id mandado en la url
      -ralizamos el mismo proceso de agreagado

      edit: la unica forma de no setear todos los datos si solamente modificamos un solo atributo
       es ir preguntando por if anidados caso contrario el usuario tiene que volver a ingresar
      todos los datos nuevamente ya sea los mismos o moficados.
       ContactEntity contactEntity = contactRepository.findById(id).get();
       contactEntity.setId(id);
       if(contactDto.getName()!=null) contactEntity.setName(contactDto.getName());
       if(contactDto.getNumber()!=0) contactEntity.setNumber(contactDto.getNumber());
        return contactMapper.ContactEntityToDto(contactEntity);
    */
    public ContactDto modifyContact(ContactDto contactDto,Integer id){
        contactDto.setId(id);
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
            return false;
        else{
             contactRepository.deleteById(id);
             return true;
            }
    }


}
