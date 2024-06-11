package com.rosendo.forumAlura.domain.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record TokenDto(
        @NotBlank String username,
        @NotBlank Boolean authenticated,
        @NotBlank Date created,
        @NotBlank Date expiration,
        @NotBlank String accessToken,
        @NotBlank String refreshToken
) {
}
