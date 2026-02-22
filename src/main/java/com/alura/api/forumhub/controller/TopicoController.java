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
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/topico")
@Tag(name = "Topico", description = "Gerenciamento de perguntas e respostas. A api permite criar, consultar, atualizar e deletar topicos dentro de uma hierarquiea de permissionamento e controle")
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
    @Operation(
        summary = "Busca tópico por ID", 
        description = "Recebe um id de um topico existente e eetorna detalhes de um tópico específico do fórum conteudo informações do titulo, mensagem, status, curso e autor"
    )
    @ApiResponse(responseCode = "200", description = "Dados retornados com sucesso")
    public ResponseEntity topicoPorId(@Parameter(name = "id", description = "ID unico que identifica o topico a ser deletado", example = "1") @PathVariable() Integer id){
        return ResponseEntity.ok(this.retornarTopico.execute(id));
    }

    /// TODO: Verificar se:
    /// - da pra buscar sem nenhum filtro
    /// - da pra buscar somente com titulo
    /// - da pra buscar somente com ano do curso
    /// - da pra buscar com ambos os filtros
    @GetMapping()
    @Operation(
        summary = "Busca tópicos com ou sem parametros", 
        description = "Permite buscar os ultimos 10 topicos atualizados, filtrados por nome do curso, por ano do curso ou por uma combinação de ambos"
    )
    public ResponseEntity listarTopicos(@PathVariable(required = false) TopicoFiltroDTO topicoFiltroDTO, @PathVariable(required = false) @PageableDefault(page = 0, size = 10, sort = "dataCriacao", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(retornarTopico.execute(topicoFiltroDTO, pageable));
    }

    @PostMapping()
    @Operation(
        summary = "Cadastrar topico", 
        description = "Permite cadastrar topicos passando titulo, mensagem, o id do curso e autor e o status"
    )
    @ApiResponse(responseCode = "201", description = "Topico criado com sucesso. Verifique os headers para consultar o caminho do topico")
    @ApiResponse(responseCode = "400", description = "Topico não cadastrado")
    public ResponseEntity cadastrarTopicos(@RequestBody @Valid CadastroTopicoDTO dto) throws URISyntaxException {
        var topicoCriado = cadastrarTopico.execute(dto);
        return ResponseEntity.created(new URI("/api/topico/" + topicoCriado.getId())).build();
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar topico", 
        description = "Permite atualizar parametros mutaveis de topicos como titulo, mensagem, curso e status"
    )
    @ApiResponse(responseCode = "201", description = "Topico atualizado com sucesso. Verifique os headers para consultar o caminho do topico")
    @ApiResponse(responseCode = "400", description = "Topico não atualizado. Verifique erro especifico")
    public ResponseEntity atualizarTopico(@PathVariable(required = true) Integer id, AtualizarTopicoDTO atualizarTopicoDTO) throws URISyntaxException {
        atualizarTopico.execute(id, atualizarTopicoDTO);
        return ResponseEntity.created(new URI("/api/topico/" + id.toString())).build();
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar topico", 
        description = "Permite atualizar parametros mutaveis de topicos como titulo, mensagem, curso e status"
    )
    @ApiResponse(responseCode = "200", description = "Topico :{id} deletado com sucesso")
    @ApiResponse(responseCode = "400", description = "Topico não existe ou não é possivel apaga-lo")
    public ResponseEntity deletarTopico(@PathVariable Integer id){
        deletarTopico.execute(id);
        return ResponseEntity.ok("Topico " + id + "deletado com sucesso");
    }
}
