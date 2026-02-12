package com.alura.api.forumhub.repository;

import com.alura.api.forumhub.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
}
