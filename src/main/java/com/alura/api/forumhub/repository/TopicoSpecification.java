package com.alura.api.forumhub.repository;

import org.springframework.data.jpa.domain.Specification;

import com.alura.api.forumhub.entity.Curso;
import com.alura.api.forumhub.entity.Topico;

import jakarta.persistence.criteria.Join;

public class TopicoSpecification {
    public static Specification<Topico> tituloContem (String titulo){
        if (titulo == null) {
            return null;
        }

        return (root, query, cb) ->  cb.like(cb.lower(root.get("titulo")), "%" + titulo.toLowerCase() + "%");
    }
    public static Specification<Topico> porNomeDoCurso (String nomeCurso) {
        return (root, query, cb) -> {
            if (nomeCurso == null || nomeCurso.isEmpty()){
                return cb.conjunction();
            }
            Join<Topico, Curso> joinCurso = root.join("curso");
            return  cb.like(cb.lower(joinCurso.get("nome")), "%" + nomeCurso.toLowerCase() + "%");
        };
    }
    // public static Specification<Topico> porAnoDoCurso (Year anoCurso){
    //     return (root, query, cb) -> anoCurso != null ? cb.equal(cb.year(root.get("data_criacao")), anoCurso) : cb.conjunction();
    // }
    
    public static Specification<Topico> porAnoDoCurso(Integer anoCurso) {
    return (root, query, cb) -> {
        if (anoCurso == null) {
            return cb.conjunction();
        }
        return cb.equal(cb.function("year", Integer.class, root.get("data_criacao")), anoCurso);
    };
}
}
