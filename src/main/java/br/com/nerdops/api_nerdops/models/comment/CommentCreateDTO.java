package br.com.nerdops.api_nerdops.models.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentCreateDTO(
        @NotBlank String content,
        @NotNull Long userId,
        @NotNull Long postId) {}
