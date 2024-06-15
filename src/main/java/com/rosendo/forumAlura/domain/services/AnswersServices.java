package com.rosendo.forumAlura.domain.services;

import com.rosendo.forumAlura.domain.dtos.AnswersRequestDto;
import com.rosendo.forumAlura.domain.models.AnswersModel;
import com.rosendo.forumAlura.domain.repositories.AnswersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswersServices {

    @Autowired
    private AnswersRepository answersRepository;

    @Transactional
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

    @Transactional
    public AnswersRequestDto updateAnswer(AnswersRequestDto answersRequestDto){
        AnswersModel answer = answersRepository.findById(answersRequestDto.topicId()).orElse(null);
        if(answer.getMessage().isEmpty()){
            BeanUtils.copyProperties(answersRequestDto, answer);
        }
        answersRepository.save(answer);
        return answersRequestDto;
    }

    @Transactional
    public void deleteAnswer(Long id){
        answersRepository.deleteById(id);
    }

    @Transactional
    public List<AnswersModel> getAnswers(Long id){
        List<AnswersModel> answers = new ArrayList<>();
        answersRepository.findById(id).ifPresent(answers::add);
        return answers;
    }
}
