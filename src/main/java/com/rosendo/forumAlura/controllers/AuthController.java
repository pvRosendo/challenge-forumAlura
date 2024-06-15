package com.rosendo.forumAlura.controllers;

import com.rosendo.forumAlura.domain.dtos.AccountCredentialsDto;
import com.rosendo.forumAlura.domain.services.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthServices authServices;

    @SuppressWarnings("rawtypes")
    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsDto data) {
        System.out.println("entrei no controller, antes de checar se tem coisa nula");
        if (authServices.checkIfParamsIsNotNull(data))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        System.out.println("n tem nada nulo, vou puxar funcao signin");
        var token = authServices.signin(data);
        System.out.println("puxei a funcao já, agr vou ver se o token ta nulo");
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        System.out.println("token n ta nulo, é p eu retornar o token");
        return token;
    }

    @SuppressWarnings("rawtypes")
    @PutMapping(value = "/refresh/{username}")
    public ResponseEntity refreshToken(@PathVariable("username") String username,
                                       @RequestHeader("Authorization") String refreshToken) {
        if (authServices.checkIfParamsIsNotNull(username, refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        var token = authServices.refreshToken(username, refreshToken);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
        return token;
    }
}
