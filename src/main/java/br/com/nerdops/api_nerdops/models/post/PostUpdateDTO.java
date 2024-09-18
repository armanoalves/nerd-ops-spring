package br.com.nerdops.api_nerdops.models.post;

import jakarta.validation.constraints.NotNull;

public record PostUpdateDTO(
        @NotNull Long id,
        String title,
        String description) {
}
