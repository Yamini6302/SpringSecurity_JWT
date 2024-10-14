package com.ust.UserDetails.controller;


import com.ust.UserDetails.dto.AuthRequest;
import com.ust.UserDetails.model.Userinfo;
import com.ust.UserDetails.service.JwtService;
import com.ust.UserDetails.service.Userservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private Userservices service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService; // Inject JwtService

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Userinfo user) {
        return ResponseEntity.ok(service.addUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getEmail()); // Generate JWT token
            return ResponseEntity.ok(token); // Return the token
        } else {
            throw new UsernameNotFoundException("Invalid login request!");
        }
    }
}