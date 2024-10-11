package br.com.nerdops.api_nerdops.controllers;

import br.com.nerdops.api_nerdops.models.comment.*;
import br.com.nerdops.api_nerdops.repositories.CommentRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("comment")
@SecurityRequirement(name = "bearer-key")
public class CommetController {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private CommentService commentService;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CommentCreateDTO data, UriComponentsBuilder builder) {
        var comment = commentService.commentCreate(data);
        repository.save(comment);

        var uri = builder.path("comment/{id}").buildAndExpand(comment.getId()).toUri();
        return ResponseEntity.created(uri).body(new CommentDetalsDTO(comment));
    }

    @GetMapping
    public ResponseEntity<Page<CommentListDTO>> list(@PageableDefault(size = 10, sort = {"content"}) Pageable pageable) {
        var page = repository.findAll(pageable).map(CommentListDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listById(@PathVariable Long id) {
        var post = repository.getReferenceById(id);
        return ResponseEntity.ok(new CommentDetalsDTO(post));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody CommentUpdateDTO dto) {
        var comment = repository.getReferenceById(dto.id());
        comment.update(dto);

        return ResponseEntity.ok(new CommentDetalsDTO(comment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
