package br.ifmg.projeto1_2026.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant criadoEm;
    private Instant atualizadoEm;

    private String nome;
    private String descricao;
    private Double preco;
    private String imgUrl;

    @ManyToMany
    @JoinTable(
            name="tb_produto_categoria", // nome da tabela
            joinColumns = @JoinColumn(name="id_produto"),
            inverseJoinColumns = @JoinColumn(name="id_categoria")
    )
    private Set<Categoria> categorias = new HashSet<Categoria>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public Instant getAtualizadoEm() {
        return atualizadoEm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @PrePersist
    public void prePersist(){
        this.criadoEm = Instant.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.atualizadoEm = Instant.now();
    }

    public Produto(Long id, Instant criadoEm, Instant atualizadoEm, String nome, String descricao, Double preco, String imgUrl) {
        this.id = id;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imgUrl = imgUrl;
    }

    public Produto() {
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", criadoEm=" + criadoEm +
                ", atualizadoEm=" + atualizadoEm +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }
}
