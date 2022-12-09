package com.API.Model.repositories;

import com.API.Model.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer>{
    Optional<UserEntity> findOneByEmail(String email);
}
