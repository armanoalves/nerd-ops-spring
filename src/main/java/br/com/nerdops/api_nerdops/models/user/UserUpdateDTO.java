package br.com.nerdops.api_nerdops.models.user;

import jakarta.validation.constraints.NotNull;

public record UserUpdateDTO(
        @NotNull Long id,
        String name,
        String email
) {
}
