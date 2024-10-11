package br.com.nerdops.api_nerdops.models.comment;

import br.com.nerdops.api_nerdops.models.ValidacaoException;
import br.com.nerdops.api_nerdops.repositories.PostRepository;
import br.com.nerdops.api_nerdops.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public Comment commentCreate(CommentCreateDTO dto) {

        if(!userRepository.existsById(dto.userId())) {
            throw new ValidacaoException("ID de usuário passado não existe no banco");
        }

        if(!postRepository.existsById(dto.postId())) {
            throw new ValidacaoException("ID do Post passado não existe no banco");
        }

        var user = userRepository.getReferenceById(dto.userId());
        var post = postRepository.getReferenceById(dto.postId());
        Date date = new Date();

        return new Comment(null, dto.content(), date, user, post);
    }
}
