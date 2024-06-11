package com.rosendo.forumAlura.controllers;

import com.rosendo.forumAlura.domain.dtos.TopicRequestDto;
import com.rosendo.forumAlura.domain.models.TopicModel;
import com.rosendo.forumAlura.domain.services.TopicServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/topics")
public class TopicControllers {

    @Autowired
    private TopicServices services;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTopic(@RequestBody @Valid TopicRequestDto topicRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(services.createTopic(topicRequest));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TopicModel>> getAllTopics(){
        return ResponseEntity.status(HttpStatus.OK).body(services.getAllTopics());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicModel> getTopicById(@PathVariable @Valid Long id){
        return ResponseEntity.status(HttpStatus.OK).body(services.getTopicById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTopicById(@PathVariable @Valid Long id){
        services.deleteTopicById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateTopic(@PathVariable @Valid Long id, @RequestBody @Valid TopicRequestDto topicRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(services.updateTopic(id, topicRequest));
    }
}
