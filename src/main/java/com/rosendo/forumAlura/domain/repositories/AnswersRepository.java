package com.rosendo.forumAlura.domain.repositories;

import com.rosendo.forumAlura.domain.models.AnswersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswersRepository extends JpaRepository<AnswersModel, Long> { }
