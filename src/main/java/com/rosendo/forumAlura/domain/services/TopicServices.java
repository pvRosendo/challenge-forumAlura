package com.rosendo.forumAlura.domain.services;

import com.rosendo.forumAlura.domain.dtos.TopicRequestDto;
import com.rosendo.forumAlura.domain.enums.EnumState;
import com.rosendo.forumAlura.domain.models.AnswersModel;
import com.rosendo.forumAlura.domain.models.TopicModel;
import com.rosendo.forumAlura.domain.repositories.AnswersRepository;
import com.rosendo.forumAlura.domain.repositories.TopicRepository;
import com.rosendo.forumAlura.exceptions.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicServices {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private AnswersRepository answersRepository;

    @Transactional
    public List<TopicModel> getAllTopics(){
        return topicRepository.findAll();
    }

    @Transactional
    public TopicModel getTopicById(Long id){
        return topicRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Don't find topic"));
    }

    @Transactional
    public TopicRequestDto createTopic(TopicRequestDto topicDto) {
        var topicModel = new TopicModel();
        var answerModel = new AnswersModel();

        BeanUtils.copyProperties(topicDto, topicModel);
        topicModel.setTopicState(EnumState.inProgress);
        topicModel.setDateCreation(LocalDateTime.now());

        if (topicRepository.existsByTitleOrMessage(topicDto.title(), topicDto.message())){
            throw new RuntimeException("A topic with this title or message already exists");
        }

        topicRepository.save(topicModel);

        answerModel.setTopicId(topicModel.getId());
        answerModel.setMessage("");
        answerModel.setAuthor("");
        answersRepository.save(answerModel);
        return topicDto;
    }

    @Transactional
    public TopicRequestDto updateTopic(Long id, TopicRequestDto topicDto) {

        topicRepository.findById(id).ifPresent(
                topic -> {
                    BeanUtils.copyProperties(topicDto, topic);
                    if (topicRepository.existsByTitleOrMessage(topicDto.title(), topicDto.message())){
                        throw new RuntimeException("A topic with this title or message already exists");
                    }
                    topicRepository.save(topic);
                }
        );

        return topicDto;
    }

    @Transactional
    public void deleteTopicById(Long id){
        topicRepository.deleteById(id);
    }

}
