package br.com.nerdops.api_nerdops.controllers;

import br.com.nerdops.api_nerdops.models.user.*;
import br.com.nerdops.api_nerdops.repositories.UserRepository;
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
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid UserCreateDTO dados, UriComponentsBuilder uriBuilder) {
        var user = new User(dados);
        repository.save(user);

        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDetalsDTO(user));

    }

    @GetMapping
    public ResponseEntity<Page<UserListDTO>> list(@PageableDefault(size = 10, sort={"name"}) Pageable pageable) {
        var page = repository.findAllByAtivoTrue(pageable).map(UserListDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listById(@PathVariable Long id) {
        var user = repository.getReferenceById(id);
        return ResponseEntity.ok(new UserDetalsDTO(user));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UserUpdateDTO dados) {
        var user = repository.getReferenceById(dados.id());
        user.update(dados);

        return ResponseEntity.ok(new UserDetalsDTO(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var user = repository.getReferenceById(id);
        user.excluir();
        return ResponseEntity.noContent().build();
    }
}