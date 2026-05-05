package br.ifmg.projeto1_2026.dto;

import br.ifmg.projeto1_2026.entity.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO {

    private Long id;
    @NotBlank(message = "Campo obrigatório")
    private String nome;
    private String telefone;
    @NotBlank(message = "Campo obrigatório")
    @Email(message = "Email Inválido")
    private String email;

    private List<PerfilDTO> perfil = new ArrayList<PerfilDTO>();

    public UsuarioDTO(Long id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.telefone = usuario.getTelefone();
        this.email = usuario.getEmail();
        usuario.getPerfil().forEach(perf->this.perfil.add(new PerfilDTO(perf)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PerfilDTO> getPerfil() {
        return perfil;
    }

    public void setPerfil(List<PerfilDTO> perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
