package com.API.Model.repositories;

import com.API.Model.Entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity,Integer>
{
    boolean existsByStreet(String street);
    boolean existsByNumber(String number);
}
