package com.rosendo.forumAlura.controllers;

import com.rosendo.forumAlura.domain.dtos.AnswersRequestDto;
import com.rosendo.forumAlura.domain.dtos.TopicRequestDto;
import com.rosendo.forumAlura.domain.services.AnswersServices;
import com.rosendo.forumAlura.domain.services.TopicServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/topics")
public class TopicControllers {

    @Autowired
    private TopicServices services;


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createTopic(@RequestBody @Valid TopicRequestDto topicRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(services.createTopic(topicRequest));
    }

    @PostMapping(
            value= "/answers",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createAnswer(@RequestBody @Valid AnswersRequestDto answersRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(services.createAnswer(answersRequestDto));
    }
}
