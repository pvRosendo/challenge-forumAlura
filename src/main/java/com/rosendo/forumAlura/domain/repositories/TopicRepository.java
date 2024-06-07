package com.rosendo.forumAlura.domain.repositories;

import com.rosendo.forumAlura.domain.models.TopicModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<TopicModel, Long> {}
