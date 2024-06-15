package com.rosendo.forumAlura.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateAccountCredentialsDto(
        @NotBlank String username,
        @NotBlank String password,
        Long permission
) { }
