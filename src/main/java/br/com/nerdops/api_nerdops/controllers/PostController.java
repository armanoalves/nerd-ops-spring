package br.com.nerdops.api_nerdops.controllers;

import br.com.nerdops.api_nerdops.models.post.Post;
import br.com.nerdops.api_nerdops.models.post.PostCreateDTO;
import br.com.nerdops.api_nerdops.models.post.PostDetalsDTO;
import br.com.nerdops.api_nerdops.repositories.PostRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastar(@RequestBody @Valid PostCreateDTO dados, UriComponentsBuilder uriBuilder) {
        var post = new Post();
        repository.save(post);

        var uri = uriBuilder.path("/post/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uri).body(new PostDetalsDTO(post));
    }

//    public ResponseEntity<Page<PostDetalsDTO>> listar(@PageableDefault(size = 10, sort = {"title"}) Pageable pageable) {
//
//    }
}
