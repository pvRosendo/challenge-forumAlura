package com.rosendo.forumAlura.controllers;

import com.rosendo.forumAlura.domain.dtos.AccountCredentialsDto;
import com.rosendo.forumAlura.domain.dtos.CreateAccountCredentialsDto;
import com.rosendo.forumAlura.domain.services.AuthServices;
import com.rosendo.forumAlura.domain.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthServices authServices;

    @Autowired
    UserServices userServices;

    @PostMapping(value = "/user")
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountCredentialsDto data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userServices.createUser(data));
    }

    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsDto data) {
        if (authServices.checkIfParamsIsNotNull(data))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        var token = authServices.signin(data);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }

    @SuppressWarnings("rawtypes")
    @PutMapping(value = "/refresh/{username}")
    public ResponseEntity refreshToken(@PathVariable("username") String username, @RequestHeader("Authorization") String refreshToken) {
        if (authServices.checkIfParamsIsNotNull(username, refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        var token = authServices.refreshToken(username, refreshToken);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }
}
