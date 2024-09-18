package br.com.nerdops.api_nerdops.models.comment;

import java.util.Date;

public record CommentDetalsDTO(String content, Date createIn) {
    public CommentDetalsDTO(Comment comment) {
        this(comment.getContent(), comment.getCreated_in());
    }
}
