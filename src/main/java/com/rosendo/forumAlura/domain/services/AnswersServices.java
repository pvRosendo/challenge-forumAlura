package com.rosendo.forumAlura.domain.services;

import com.rosendo.forumAlura.domain.dtos.AnswersRequestDto;
import com.rosendo.forumAlura.domain.models.AnswersModel;
import com.rosendo.forumAlura.domain.repositories.AnswersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswersServices {

    @Autowired
    AnswersRepository answersRepository;
}
