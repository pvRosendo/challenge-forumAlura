package com.rosendo.forumAlura.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswersRequestDto(
        @NotBlank String author,
        @NotBlank String message,
        @NotNull Long topicId
        ) { }
