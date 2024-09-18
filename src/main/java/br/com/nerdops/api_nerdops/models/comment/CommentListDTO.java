package br.com.nerdops.api_nerdops.models.comment;

import java.util.Date;

public record CommentListDTO(Long id, String content, Date createIn, Long userId, Long postId) {
    public CommentListDTO(Comment comment) {
        this(comment.getId(), comment.getContent(), comment.getCreated_in(),
                comment.getUser().getId(), comment.getPost().getId());
    }
}
