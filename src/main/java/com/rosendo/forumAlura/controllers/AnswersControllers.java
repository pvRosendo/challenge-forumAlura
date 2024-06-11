package com.rosendo.forumAlura.controllers;

import com.rosendo.forumAlura.domain.dtos.AnswersRequestDto;
import com.rosendo.forumAlura.domain.models.AnswersModel;
import com.rosendo.forumAlura.domain.services.AnswersServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/topics/answers")
public class AnswersControllers {

    @Autowired
    private AnswersServices services;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnswersModel>> getAnswersById(@PathVariable @Valid Long id){
        return ResponseEntity.status(HttpStatus.OK).body(services.getAnswers(id));
    }

    @PostMapping(value= "/answers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAnswer(@RequestBody @Valid AnswersRequestDto answersRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(services.createAnswer(answersRequestDto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAnswerById(@PathVariable @Valid Long id){
        services.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateTopic(@PathVariable @Valid Long id, @RequestBody @Valid AnswersRequestDto answersRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(services.updateAnswer(answersRequestDto));
    }
}
