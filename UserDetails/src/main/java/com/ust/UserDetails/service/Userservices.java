package com.ust.UserDetails.service;


import com.ust.UserDetails.model.Userinfo;
import com.ust.UserDetails.repository.Userinforepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Userservices {

    @Autowired
    private Userinforepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(Userinfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword())); // Ensure this line is present
        repo.save(userInfo);
        return "User added to system.";
    }

    public Userinfo getUserByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }
}
