package com.rosendo.forumAlura.domain.services;

import com.rosendo.forumAlura.domain.dtos.AnswersRequestDto;
import com.rosendo.forumAlura.domain.dtos.TopicRequestDto;
import com.rosendo.forumAlura.domain.enums.EnumState;
import com.rosendo.forumAlura.domain.models.AnswersModel;
import com.rosendo.forumAlura.domain.models.TopicModel;
import com.rosendo.forumAlura.domain.repositories.AnswersRepository;
import com.rosendo.forumAlura.domain.repositories.TopicRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TopicServices {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private AnswersRepository answersRepository;

    public TopicModel createTopic(TopicRequestDto topicDto) {
        var topicModel = new TopicModel();
        var answerModel = new AnswersModel();

        BeanUtils.copyProperties(topicDto, topicModel);
        topicModel.setTopicState(EnumState.inProgress);
        topicModel.setDateCreation(LocalDateTime.now());
        topicRepository.save(topicModel);

        answerModel.setTopicId(topicModel.getId());
        answerModel.setMessage("");
        answerModel.setAuthor("");
        answersRepository.save(answerModel);
        return topicModel;
    }

    public AnswersRequestDto createAnswer(AnswersRequestDto answersRequestDto){
        AnswersModel answer = answersRepository.findById(answersRequestDto.topicId()).orElse(null);

        if(answer.getMessage().isEmpty()){
            BeanUtils.copyProperties(answersRequestDto, answer);
            answersRepository.save(answer);
        }else{
            AnswersModel newAnswer = new AnswersModel();
            BeanUtils.copyProperties(answersRequestDto, newAnswer);
            answersRepository.save(newAnswer);
        }

        return answersRequestDto;
    }

}
