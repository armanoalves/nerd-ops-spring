package br.com.nerdops.api_nerdops.models.comment;

import jakarta.validation.constraints.NotNull;

public record CommentUpdateDTO(@NotNull Long id, String content) {
}
