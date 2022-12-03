package com.API.Model.repositories;

import com.API.Model.Entity.AddressEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<AddressEntity,Integer>
{
    /*SELECCION WHERE*/
    Page<AddressEntity> findAllByStreetContains(String street, Pageable pageable);
    /*SELECCION WHERE*/
    Page<AddressEntity> findAllByNumberContains(String number,Pageable pageable);
    /*INTERSECCION*/
    Page<AddressEntity> findAllByStreetContainsAndNumberContains(String street,String number,Pageable pageable);

    List<AddressEntity> findAllByStreetAndNumber(String street,String number);
}

/*                  ------PRUEBAS------
  Page<AddressEntity> findById(Integer id,Pageable pageable);

  extends JpaRepository<AddressEntity,Integer>{}
 */
