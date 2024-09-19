package br.com.nerdops.api_nerdops.models.post;

import br.com.nerdops.api_nerdops.models.ValidacaoException;
import br.com.nerdops.api_nerdops.repositories.CommentRepository;
import br.com.nerdops.api_nerdops.repositories.PostRepository;
import br.com.nerdops.api_nerdops.repositories.UserRepository;
import io.micrometer.observation.Observation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public Post createPost(PostCreateDTO dto) {

        if(!userRepository.existsById(dto.userid())) {
            throw new ValidacaoException("ID do usuário não encontrado");
        }

        var user = userRepository.getReferenceById(dto.userid());
        Date date = new Date();

        return new Post(null, dto.title(), dto.description(), date, user, null);
    }

    public Optional<?> postSearch(PostSearchDTO dto) {

        if(dto.title() != null) {
            return Optional.ofNullable(postRepository.findPostByTitleContainingIgnoreCase(dto.title()));

        } else if (dto.name() != null) {

            var userByName = userRepository.findUserByNameContainingIgnoreCase(dto.name());
            List<Long> postIds = userByName.getPosts().stream()
                    .map(Post::getId)
                    .collect(Collectors.toList());
            return Optional.ofNullable(postRepository.findAllById(postIds));

        }
        return Optional.of(new ValidacaoException("Campos Title e Name estão vazios"));

    }
}
