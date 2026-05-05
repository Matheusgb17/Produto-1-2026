package br.ifmg.projeto1_2026.dto;

import br.ifmg.projeto1_2026.entity.Usuario;

public class UsuarioInsertDTO extends UsuarioDTO {
    private String senha;

    public UsuarioInsertDTO(Long id, String nome, String telefone, String email) {
        super(id, nome, telefone, email);
    }

    public UsuarioInsertDTO(Usuario usuario) {
        super(usuario);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}
