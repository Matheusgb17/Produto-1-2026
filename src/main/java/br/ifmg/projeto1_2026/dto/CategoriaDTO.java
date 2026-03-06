package br.ifmg.projeto1_2026.dto;

import br.ifmg.projeto1_2026.entity.Categoria;

public class CategoriaDTO {
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaDTO(Categoria categoria) {
        this.nome = categoria.getNome();
        this.id = categoria.getId();
    }

    public CategoriaDTO(String nome) {
        this.nome = nome;
    }

    private String nome;
    private Long id;
}
