package br.ifmg.projeto1_2026.dto;

import br.ifmg.projeto1_2026.entity.Categoria;

public class CategoriaDTO {

    private Long id;
    private String nome;

    public CategoriaDTO() {}

    public CategoriaDTO(String nome, Long id) {
        this.nome = nome;
        this.id = id;
    }

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CategoriaDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }


}
