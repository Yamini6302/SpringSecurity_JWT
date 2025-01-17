package com.ust.UserDetails.repository;

import com.ust.UserDetails.model.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Userinforepository extends JpaRepository<Userinfo, Integer> {
    Optional<Userinfo> findByEmail(String email);
}