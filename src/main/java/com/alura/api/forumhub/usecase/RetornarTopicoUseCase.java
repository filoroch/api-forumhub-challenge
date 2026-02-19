package com.alura.api.forumhub.usecase;

import com.alura.api.forumhub.dto.OutputTopicoDTO;
import com.alura.api.forumhub.dto.TopicoFiltroDTO;
import com.alura.api.forumhub.entity.Topico;
import com.alura.api.forumhub.repository.ITopicoRepository;
import com.alura.api.forumhub.repository.TopicoSpecification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class RetornarTopicoUseCase {

    private ITopicoRepository repository;

    public OutputTopicoDTO execute(Integer id){
        var topicoExists =  repository.findById(id).orElseThrow(() -> new RuntimeException("O ID nãe existe"));;
        return new OutputTopicoDTO(
                topicoExists.getTitulo(),
                topicoExists.getMensagem(),
                topicoExists.getStatus(),
                topicoExists.getAutor(),
                topicoExists.getCurso()
        );
    }

    public List<OutputTopicoDTO> execute(TopicoFiltroDTO input, Pageable pageable){

        Specification<Topico> filter = TopicoSpecification.porNomeDoCurso(input.nomeCurso())
                .and(TopicoSpecification.porAnoDoCurso(input.anoCurso()));

        var repositoryEntity = repository.findAll(filter, pageable);
        var outputEntity = repositoryEntity.stream()
                .map(topico -> new OutputTopicoDTO(topico.getTitulo(), topico.getMensagem(), topico.getStatus(), topico.getAutor(), topico.getCurso()))
                .toList();

        return outputEntity;
    }
}
