package com.API.Model.Entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;

@Data
@Table(name = "contacts")
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
    @Column(name = "number",columnDefinition = "INT(10)")
    int number;
}