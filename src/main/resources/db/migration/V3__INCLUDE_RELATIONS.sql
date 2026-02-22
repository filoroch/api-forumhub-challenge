ALTER TABLE topico
ADD COLUMN curso INTEGER NOT NULL,
ADD COLUMN respostas INTEGER NOT NULL,
ADD CONSTRAINT fk_topico_curso
    FOREIGN KEY (curso)
    REFERENCES curso(id),
ADD CONSTRAINT fk_topico_resposta
    FOREIGN KEY (respostas)
    REFERENCES resposta(id);

ALTER TABLE resposta
ADD COLUMN autor INTEGER NOT NULL,
ADD CONSTRAINT fk_resposta_usuario
    FOREIGN KEY (autor)
    REFERENCES usuario(id);

ALTER TABLE USUARIO
ADD COLUMN perfis INTEGER NOT NULL,
ADD CONSTRAINT fk_usuario_perfil
      FOREIGN KEY (perfis)
      REFERENCES perfil(id);
