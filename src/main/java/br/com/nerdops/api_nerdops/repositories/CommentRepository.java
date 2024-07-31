package br.com.nerdops.api_nerdops.repositories;

import br.com.nerdops.api_nerdops.models.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
