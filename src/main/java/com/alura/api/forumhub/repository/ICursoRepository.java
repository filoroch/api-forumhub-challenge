package com.alura.api.forumhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alura.api.forumhub.entity.Curso;

public interface ICursoRepository extends JpaRepository<Curso, Integer> {
}
