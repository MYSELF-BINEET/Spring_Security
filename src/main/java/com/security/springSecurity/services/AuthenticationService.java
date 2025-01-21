package com.security.springSecurity.services;

import com.security.springSecurity.dto.JwtAuthenticationResponse;
import com.security.springSecurity.dto.RefreshTokenRequest;
import com.security.springSecurity.dto.SignInRequest;
import com.security.springSecurity.dto.SignUpRequest;
import com.security.springSecurity.entites.User;

public interface AuthenticationService {

    User signUp(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signIn(SignInRequest signInRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
