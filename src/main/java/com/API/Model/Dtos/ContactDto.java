package com.API.Model.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto
{
     Integer id;
     String name;
     int phone;
     AddressDto address;
}
