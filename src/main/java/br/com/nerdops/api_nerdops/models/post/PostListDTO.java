package br.com.nerdops.api_nerdops.models.post;

import br.com.nerdops.api_nerdops.models.comment.Comment;

import java.util.ArrayList;
import java.util.List;

public record PostListDTO(Long id, String title, String description, Long userId, List<Long> comments) {
    public PostListDTO(Post post) {
        this(
            post.getId(), post.getTitle(), post.getDescription(), post.getUser().getId(),
                (post.getComments() != null) ? post.getComments()
                        .stream().map(Comment::getId).toList() : new ArrayList<>()
        );
    }
}
