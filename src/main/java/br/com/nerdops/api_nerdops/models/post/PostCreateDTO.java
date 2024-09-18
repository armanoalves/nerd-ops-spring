package br.com.nerdops.api_nerdops.models.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostCreateDTO(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotNull
        Long userid
) {
}
