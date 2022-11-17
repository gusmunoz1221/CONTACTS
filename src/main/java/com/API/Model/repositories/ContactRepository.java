package com.API.Model.repositories;

import com.API.Model.Entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity,Integer>
{
    boolean existsByName(String name);
    boolean existsByNumber(int number);
}