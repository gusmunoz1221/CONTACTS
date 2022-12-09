package com.API.Model.Entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Table(name = "contact")
@Entity
public class UserEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(10) UNSIGNED")
    public Integer id;

    @Column(name = "name", columnDefinition = "VARCHAR(45)")
    public String name;
    @Column(name = "email", columnDefinition = "VARCHAR(45)")
    public String email;
    @Column(name = "password", columnDefinition = "VARCHAR(255)")
    public String password;
}
