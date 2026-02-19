package com.alura.api.forumhub.controller;

import com.alura.api.forumhub.dto.AtualizarTopicoDTO;
import com.alura.api.forumhub.dto.CadastroTopicoDTO;
import com.alura.api.forumhub.dto.TopicoFiltroDTO;
import com.alura.api.forumhub.repository.ITopicoRepository;
import com.alura.api.forumhub.usecase.AtualizarTopicoUseCase;
import com.alura.api.forumhub.usecase.CadastrarTopicoUseCase;
import com.alura.api.forumhub.usecase.DeletarTopicoUseCase;
import com.alura.api.forumhub.usecase.RetornarTopicoUseCase;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/topico")
public class TopicoController {

    private CadastrarTopicoUseCase cadastrarTopico;
    private RetornarTopicoUseCase retornarTopico;
    private AtualizarTopicoUseCase atualizarTopico;
    private DeletarTopicoUseCase deletarTopico;

    public TopicoController(CadastrarTopicoUseCase cadastrarTopico, ITopicoRepository repository){
        this.cadastrarTopico = cadastrarTopico;
        this.retornarTopico = retornarTopico;
        this.atualizarTopico = atualizarTopico;
        this.deletarTopico = deletarTopico;
    }

    @GetMapping("/{id}")
    public ResponseEntity topicoPorId(@PathVariable Integer id, AtualizarTopicoDTO atualizarTopico){
        return ResponseEntity.ok(this.retornarTopico.execute(id));
    }

    /// TODO: Verificar se:
    /// - da pra buscar sem nenhum filtro
    /// - da pra buscar somente com titulo
    /// - da pra buscar somente com ano do curso
    /// - da pra buscar com ambos os filtros
    @GetMapping()
    public ResponseEntity listarTopicos(TopicoFiltroDTO topicoFiltroDTO, @PageableDefault(page = 0, size = 10, sort = "dataCriacao", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(retornarTopico.execute(topicoFiltroDTO, pageable));
    }

    @PostMapping()
    public ResponseEntity cadastrarTopicos(@RequestBody @Valid CadastroTopicoDTO dto) throws URISyntaxException {
        var topicoCriado = cadastrarTopico.execute(dto);
        return ResponseEntity.created(new URI("/api/topico/" + topicoCriado.getId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarTopico(@PathVariable(required = true) Integer id, AtualizarTopicoDTO atualizarTopicoDTO) throws URISyntaxException {
        atualizarTopico.execute(id, atualizarTopicoDTO);
        return ResponseEntity.created(new URI("/api/topico/" + id.toString())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarTopico(@PathVariable Integer id){
        deletarTopico.execute(id);
        return ResponseEntity.ok("Topico deletado com sucesso");
    }
}
