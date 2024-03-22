package com.practice.bloggerapp.service;

import com.practice.bloggerapp.payload.LoginDto;
import com.practice.bloggerapp.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
