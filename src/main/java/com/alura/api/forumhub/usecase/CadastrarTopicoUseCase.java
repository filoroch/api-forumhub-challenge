package com.alura.api.forumhub.usecase;

import com.alura.api.forumhub.dto.CadastroTopicoDTO;
import com.alura.api.forumhub.entity.Curso;
import com.alura.api.forumhub.entity.Status;
import com.alura.api.forumhub.entity.Topico;
import com.alura.api.forumhub.entity.Usuario;
import com.alura.api.forumhub.repository.ICursoRepository;
import com.alura.api.forumhub.repository.ITopicoRepository;
import com.alura.api.forumhub.repository.IUsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CadastrarTopicoUseCase {

    private ITopicoRepository repository;
    private IUsuarioRepository autorRepository;
    private ICursoRepository cursoRepository;

    public CadastrarTopicoUseCase(
            ITopicoRepository repository,
            IUsuarioRepository autorRepository,
            ICursoRepository cursoRepository
    ){
        this.repository = repository;
        this.autorRepository = autorRepository;
        this.cursoRepository = cursoRepository;
    }

    public Topico execute(CadastroTopicoDTO dto){
        if (repository.existsByTituloAndMensagem(dto.titulo(), dto.mensagem())){
            throw new RuntimeException("Não é possivel registrar um topico com titulo e mensagem já existentes");
        }
        if (!autorRepository.existsById(dto.autor_id())){
            throw new RuntimeException("Autor invalido: " + dto.autor_id());
        }
        if (!cursoRepository.existsById(dto.curso_id())){
            throw new RuntimeException("Curso invalido: " + dto.curso_id());
        }

        var autor = autorRepository.findById(dto.autor_id()).get();
        var curso = cursoRepository.findById(dto.autor_id()).get();
        var novoTopico = new Topico(null, dto.titulo(), dto.mensagem(), LocalDate.now(), Status.CRIADO, autor, curso, null);

        return repository.saveAndFlush(novoTopico);
    }
}
