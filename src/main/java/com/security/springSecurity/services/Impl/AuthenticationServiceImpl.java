package com.security.springSecurity.services.Impl;


import com.security.springSecurity.dto.JwtAuthenticationResponse;
import com.security.springSecurity.dto.RefreshTokenRequest;
import com.security.springSecurity.dto.SignInRequest;
import com.security.springSecurity.dto.SignUpRequest;
import com.security.springSecurity.entites.Role;
import com.security.springSecurity.entites.User;
import com.security.springSecurity.repository.UserRepository;
import com.security.springSecurity.services.AuthenticationService;
import com.security.springSecurity.services.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    final private UserRepository userRepository;

    final private PasswordEncoder passwordEncoder;

    final private AuthenticationManager authenticationManager;

    final private JwtService jwtService;

    public AuthenticationServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository=userRepository;
        this.authenticationManager=authenticationManager;
        this.jwtService = jwtService;
    }


    @Override
    public User signUp(SignUpRequest signUpRequest){
        User user=new User();

        user.setEmail(signUpRequest.getEmail());
        user.setFirstname(signUpRequest.getFirstName());
        user.setLastname(signUpRequest.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));


        return userRepository.save(user);
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword()));

        var user=userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(()-> new IllegalArgumentException("Invalid Email or Password"));

        var jwt=jwtService.generateToken(user);
        var refreshToken=jwtService.generateRefreshToken(new HashMap<>(),user);

        JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return  jwtAuthenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail=jwtService.extractUsername(refreshTokenRequest.getToken());
        User user=userRepository.findByEmail(userEmail).orElseThrow();

        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){
            var jwt=jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return  jwtAuthenticationResponse;
        }

        return null;
    }
}
