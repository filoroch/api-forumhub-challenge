package com.alura.api.forumhub.usecase;

import com.alura.api.forumhub.repository.ITopicoRepository;

public class DeletarTopicoUseCase {

    private ITopicoRepository repository;

    public DeletarTopicoUseCase(ITopicoRepository repository){
        this.repository = repository;
    }

    public void execute (Integer id){
        if (!repository.existsById(id)){
            throw new RuntimeException("O Id do topico fornecido não existe");
        }
        repository.deleteById(id);
    }
}
