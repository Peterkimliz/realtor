package com.example.digirealtor.Security;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
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
        Optional< UserModel> foundUser=userRepository.findByEmail(username);
        if(foundUser.isPresent()){
          return  new User(foundUser.get().getEmail(),foundUser.get().getPassword(),new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User not found");
        }

    }

}
