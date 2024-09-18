package br.com.nerdops.api_nerdops.models.post;

import br.com.nerdops.api_nerdops.models.ValidacaoException;
import br.com.nerdops.api_nerdops.repositories.CommentRepository;
import br.com.nerdops.api_nerdops.repositories.PostRepository;
import br.com.nerdops.api_nerdops.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostService {

    @Autowired
    private UserRepository userRepository;

    public Post createPost(PostCreateDTO dto) {

        if(!userRepository.existsById(dto.userid())) {
            throw new ValidacaoException("ID do usuário não encontrado");
        }

        var user = userRepository.getReferenceById(dto.userid());
        Date date = new Date();

        return new Post(null, dto.title(), dto.description(), date, user, null);
    }
}
