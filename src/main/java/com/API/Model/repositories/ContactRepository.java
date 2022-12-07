package com.API.Model.repositories;

import com.API.Model.Entity.AddressEntity;
import com.API.Model.Entity.ContactEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<ContactEntity,Integer>
{
    boolean existsByName(String name);
    boolean existsByPhone(String phone);
    /*SELECCION*/
    Page<ContactEntity> findAllByNameContains(String name, Pageable pageable);
    /*SELECCION*/
    Page<ContactEntity> findAllByPhoneContains(String phone,Pageable pageable);
    /*INTERSECCION*/
    Page<ContactEntity> findAllByNameContainsAndPhoneContains(String name,String phone,Pageable pageable);

    List<ContactEntity> findByAddress(AddressEntity address);

}
/*
                  ------PRUEBAS------
  Page<ContactEntity> findById(Integer id,Pageable pageable);
  Page<ContactEntity> findByName(String name,Pageable pageable);
  Page<ContactEntity> findByPhone(String phone,Pageable pageable);

  extends JpaRepository<ContactEntity,Integer>{}
 */