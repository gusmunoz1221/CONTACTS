package com.API.Model.Entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import javax.persistence.*;

@Data
@Table(name = "contact")
@RequiredArgsConstructor
@Entity
public class ContactEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(10) UNSIGNED")
    Integer id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    String name;
    @Column(name = "phone",columnDefinition = "VARCHAR(50)")
    String phone;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
    AddressEntity address;
}