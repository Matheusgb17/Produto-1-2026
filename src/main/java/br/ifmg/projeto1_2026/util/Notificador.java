package br.ifmg.projeto1_2026.util;

import br.ifmg.projeto1_2026.entity.Usuario;

public interface Notificador {
    public void notificar(Usuario usuario, String mensagem);
}
