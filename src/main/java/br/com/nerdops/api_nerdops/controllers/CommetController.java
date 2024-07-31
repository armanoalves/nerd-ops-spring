package br.com.nerdops.api_nerdops.controllers;

import br.com.nerdops.api_nerdops.repositories.CommentRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("commet")
public class CommetController {

    private CommentRepository repository;
}
