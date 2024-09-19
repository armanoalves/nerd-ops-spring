package br.com.nerdops.api_nerdops.controllers;

import br.com.nerdops.api_nerdops.models.post.*;
import br.com.nerdops.api_nerdops.repositories.PostRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostRepository repository;

    @Autowired
    private PostService postService;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid PostCreateDTO dados, UriComponentsBuilder uriBuilder) {
        var post = postService.createPost(dados);
        repository.save(post);
        var uri = uriBuilder.path("/post/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uri).body(new PostDetalsDTO(post));
    }

    @GetMapping
    public ResponseEntity<Page<PostListDTO>> list(@PageableDefault(size = 10, sort = {"title"}) Pageable pageable) {
        var page = repository.findAll(pageable).map(PostListDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listById(@PathVariable Long id) {
        var post = repository.getReferenceById(id);
        return ResponseEntity.ok(new PostDetalsDTO(post));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody PostUpdateDTO dados) {
        var post = repository.getReferenceById(dados.id());
        post.update(dados);
        return ResponseEntity.ok(new PostDetalsDTO(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<?> findByPostNameOrUserName(@RequestBody PostSearchDTO dto) {
        Optional<?> result = postService.postSearch(dto);

        if (result.isPresent()) {
            Object value = result.get();

            if (value instanceof Post) {
                Post post = (Post) value;
                return ResponseEntity.ok(new PostDetalsDTO(post));
            } else if (value instanceof List) {
                List<Post> posts = (List<Post>) value;

                List<PostDetalsDTO> postDto = posts.stream()
                        .map(PostDetalsDTO::new).collect(Collectors.toList());

                return ResponseEntity.ok(postDto);
            }
        }

        return ResponseEntity.badRequest().body("Nenhum resultado encontrado");
    }
}
