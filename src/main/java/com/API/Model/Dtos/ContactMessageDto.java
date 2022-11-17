package com.API.Model.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/*
    agrega el massage por defecto y no en el mapping
*/
public class ContactMessageDto
{
    Integer id;
    String message="agregado correctamente";
}
