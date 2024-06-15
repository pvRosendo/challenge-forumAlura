package com.rosendo.forumAlura.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public record TokenDto(
        @NotBlank String username,
        @NotNull Boolean authenticated,
        @NotBlank Date created,
        @NotBlank Date expiration,
        @NotBlank String accessToken,
        @NotBlank String refreshToken
) {
    public TokenDto() {
        this("defaultName",
                false,
                Date.from(Instant.now()),
                Date.from(Instant.now().plus(Duration.ofHours(2))),
                "",
                ""
        );
    }
}
