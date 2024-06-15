package com.rosendo.forumAlura.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRequestDto(
        @NotBlank String title,
        @NotBlank String message,
        @NotBlank String author,
        @NotNull String course) {}
