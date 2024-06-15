package com.rosendo.forumAlura.domain.services;

import com.rosendo.forumAlura.domain.dtos.AccountCredentialsDto;
import com.rosendo.forumAlura.domain.dtos.TokenDto;
import com.rosendo.forumAlura.domain.repositories.UserRepository;
import com.rosendo.forumAlura.infrastructure.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;

    @SuppressWarnings("rawtypes")
    public ResponseEntity signin(AccountCredentialsDto data) {
        try {
            var username = data.username();
            var password = data.password();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = repository.findByUsername(username);

            var tokenResponse = new TokenDto();
            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username, String refreshToken) {
        var user = repository.findByUsername(username);

        var tokenResponse = new TokenDto();
        if (user != null) {
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
        return ResponseEntity.ok(tokenResponse);
    }

    public boolean checkIfParamsIsNotNull(String username, String refreshToken) {
        return refreshToken == null || refreshToken.isBlank() ||
                username == null || username.isBlank();
    }

    public boolean checkIfParamsIsNotNull(AccountCredentialsDto data) {
        return data == null || data.username() == null || data.username().isBlank()
                || data.password() == null || data.password().isBlank();
    }
}
