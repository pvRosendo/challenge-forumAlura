package com.rosendo.forumAlura.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public record AccountCredentialsDto(@NotBlank String username, @NotBlank String password) {}
