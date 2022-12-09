package com.API.SecurityConfiguration;

import com.API.Model.Entity.UserEntity;
import com.API.Model.repositories.UserRepository;
import com.API.exceptions.UserUnexistingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UserUnexistingException{
        UserEntity user = userRepository.findOneByEmail(email)
                                        .orElseThrow(() -> new UserUnexistingException("usuario con email " + email + " no existe..."));
        return new UserDetailsImpl(user);
    }
}