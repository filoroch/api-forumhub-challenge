package com.alura.api.forumhub.controller;

import com.alura.api.forumhub.dto.CadastroTopicoDTO;
import com.alura.api.forumhub.usecase.CadastrarTopicoUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/topico")
public class TopicoController {

    private CadastrarTopicoUseCase cadastrarTopico;

    public TopicoController(CadastrarTopicoUseCase cadastrarTopico){
        this.cadastrarTopico = cadastrarTopico;
    }

    @PostMapping()
    public ResponseEntity cadastrarTopico(@RequestBody @Valid CadastroTopicoDTO dto) throws URISyntaxException {
        var topicoCriado = cadastrarTopico.execute(dto);
        return ResponseEntity.created(new URI("/api/topico/" + topicoCriado.getId())).build();
    }
}
