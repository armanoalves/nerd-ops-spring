package br.com.nerdops.api_nerdops.models.post;

import br.com.nerdops.api_nerdops.models.comment.Comment;

import java.util.ArrayList;
import java.util.List;

public record PostDetalsDTO(String title, String description, Long user, List<Long> comments) {
    public PostDetalsDTO(Post post) {
        this(post.getTitle(), post.getDescription(), post.getUser().getId(),
                (post.getComments() != null) ? post.getComments()
                        .stream().map(Comment::getId).toList() : new ArrayList<>()
        );
    }
}
