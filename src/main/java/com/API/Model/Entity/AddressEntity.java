package com.API.Model.Entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;

@Data
@Table(name = "address")
@RequiredArgsConstructor
@Entity
public class AddressEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idaddress", columnDefinition = "INT(10) UNSIGNED")
    Integer idaddress;

    @Column(name = "street", columnDefinition = "VARCHAR(45)")
    String street;
    @Column(name = "number", columnDefinition = "VARCHAR(45)")
    String number;
}
