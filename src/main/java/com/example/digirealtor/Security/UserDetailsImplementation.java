package com.example.digirealtor.Security;

import java.util.Optional;

import org.apache.catalina.mbeans.UserMBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.digirealtor.Models.UserModel;
import com.example.digirealtor.Repositories.UserRepository;

@Service
public class UserDetailsImplementation implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        UserModel foundUser=userRepository.findByEmail(username).get();
       
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }

}
