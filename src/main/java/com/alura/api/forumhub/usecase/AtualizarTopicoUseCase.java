package com.alura.api.forumhub.usecase;

import com.alura.api.forumhub.dto.AtualizarTopicoDTO;
import com.alura.api.forumhub.repository.ICursoRepository;
import com.alura.api.forumhub.repository.ITopicoRepository;
import com.alura.api.forumhub.repository.IUsuarioRepository;

public class AtualizarTopicoUseCase {

    private ITopicoRepository repository;
    private IUsuarioRepository autorRepository;
    private ICursoRepository cursoRepository;

    public AtualizarTopicoUseCase(
            ITopicoRepository repository,
            IUsuarioRepository autorRepository,
            ICursoRepository cursoRepository
    ){
        this.repository = repository;
        this.autorRepository = autorRepository;
        this.cursoRepository = cursoRepository;
    }

    public void execute(Integer topicoId, AtualizarTopicoDTO dto){

        var topicoExistente = repository.findById(topicoId).orElseThrow(() -> new RuntimeException("ID invalido"));

        if (!dto.titulo().isEmpty() || !dto.titulo().isBlank()){
            topicoExistente.setTitulo(dto.titulo());
        }

        if (!dto.mensagem().isEmpty() || !dto.mensagem().isBlank()){
            topicoExistente.setMensagem(dto.mensagem());
        }

        if (dto.autor_id() != null){
            topicoExistente.setAutor(autorRepository.findById(dto.autor_id())
                    .orElseThrow(() -> new RuntimeException("ID de autor invalido")));
        }
        if (dto.curso_id() != null){
            topicoExistente.setCurso(cursoRepository.findById(dto.curso_id())
                    .orElseThrow(() -> new RuntimeException("ID de curso invalido")));
        }

        repository.saveAndFlush(topicoExistente);
    }
}
