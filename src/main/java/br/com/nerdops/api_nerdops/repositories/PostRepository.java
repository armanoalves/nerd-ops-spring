package br.com.nerdops.api_nerdops.repositories;

import br.com.nerdops.api_nerdops.models.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
