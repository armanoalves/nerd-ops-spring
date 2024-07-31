package br.com.nerdops.api_nerdops.models.post;

import br.com.nerdops.api_nerdops.models.comment.Comment;
import br.com.nerdops.api_nerdops.models.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PostCreateDTO(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotNull
        User user,
        @NotNull
        List<Comment> comments
) {
}
