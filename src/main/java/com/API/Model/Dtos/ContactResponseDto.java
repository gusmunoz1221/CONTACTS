package com.API.Model.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponseDto{
    Integer id;
    String name;
    String phone;
    AddressResponseDto address;
}
