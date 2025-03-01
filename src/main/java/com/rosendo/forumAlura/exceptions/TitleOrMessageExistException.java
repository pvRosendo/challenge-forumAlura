package com.rosendo.forumAlura.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TitleOrMessageExistException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public TitleOrMessageExistException(String message){
        super(message);
    }
}
