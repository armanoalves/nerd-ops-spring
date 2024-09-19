package br.com.nerdops.api_nerdops.repositories;

import br.com.nerdops.api_nerdops.models.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findPostByTitleContainingIgnoreCase(String title);

    List<Post> findAllById(Iterable<Long> ids);
}
