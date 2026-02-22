-- Adicionar coluna autor que está faltando
ALTER TABLE topico
    ADD COLUMN autor INTEGER NOT NULL,
    ADD CONSTRAINT fk_topico_autor
        FOREIGN KEY (autor) REFERENCES usuario(id);

-- A tabela resposta já tem a FK para topico (conforme seu DDL posterior)
