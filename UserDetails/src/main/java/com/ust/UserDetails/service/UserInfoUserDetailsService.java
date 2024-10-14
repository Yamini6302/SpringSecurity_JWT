package com.ust.UserDetails.service;

import com.ust.UserDetails.model.Userinfo;
import com.ust.UserDetails.repository.Userinforepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private Userinforepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Userinfo userInfo = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                userInfo.getEmail(), userInfo.getPassword(),
                true, true, true, true,
                AuthorityUtils.createAuthorityList(userInfo.getRoles().split(",")));
    }
}