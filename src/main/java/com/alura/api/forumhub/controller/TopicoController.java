package com.alura.api.forumhub.controller;

import com.alura.api.forumhub.dto.CadastroTopicoDTO;
import com.alura.api.forumhub.entity.Topico;
import com.alura.api.forumhub.repository.ITopicoRepository;
import com.alura.api.forumhub.repository.TopicoSpecification;
import com.alura.api.forumhub.usecase.CadastrarTopicoUseCase;
import jakarta.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/topico")
public class TopicoController {

    private CadastrarTopicoUseCase cadastrarTopico;
    private ITopicoRepository repository;

    public TopicoController(CadastrarTopicoUseCase cadastrarTopico, ITopicoRepository repository){
        this.cadastrarTopico = cadastrarTopico;
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity topicoPorId(@PathVariable Integer id){
        return ResponseEntity.ok(repository.findById(id));
    }

    @GetMapping()
    public ResponseEntity listarTopicos(
        @RequestParam(required = false) String nomeCurso,
        @RequestParam(required = false) Integer anoCurso,
        @PageableDefault(page = 0, size = 20, sort = "dataCriacao", direction = Sort.Direction.ASC) Pageable pageable
    ){
        // Separar num use case especifico ou query
        Specification<Topico> spec = TopicoSpecification.porNomeDoCurso(nomeCurso)
            .and(TopicoSpecification.porAnoDoCurso(anoCurso));

        return ResponseEntity.ok(repository.findAll(spec, pageable));
    }

    @PostMapping()
    public ResponseEntity cadastrarTopico(@RequestBody @Valid CadastroTopicoDTO dto) throws URISyntaxException {
        var topicoCriado = cadastrarTopico.execute(dto);
        return ResponseEntity.created(new URI("/api/topico/" + topicoCriado.getId())).build();
    }
}
