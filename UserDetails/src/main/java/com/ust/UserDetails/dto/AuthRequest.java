package com.ust.UserDetails.dto;

import lombok.Data;

@Data
public class AuthRequest {

    private String email; // Changed from username to email for clarity

    private String password;
}