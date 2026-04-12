package br.ifmg.projeto1_2026.service;

import br.ifmg.projeto1_2026.entity.Usuario;
import br.ifmg.projeto1_2026.util.Notificador;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtivacaoClienteService {

    @Autowired
    private Notificador notificador;


    // private List<Notificador> notificadores;

    /*
    @Autowired
    public AtivacaoClienteService(List<Notificador> notificadores){
        System.out.println("Iniciando AtivacaoClienteService");
        this.notificadores = notificadores;
    }*/

    public AtivacaoClienteService(){
        System.out.println("Iniciando AtivacaoClienteService com construtor sem parametros");
    }

    public void ativar(Usuario usuario, String mensagem){
        if(notificador != null)
            notificador.notificar(usuario, mensagem);
    //        for(Notificador notificador : notificadores){
    //            notificador.notificar(usuario, mensagem);
    //        }
    }

    @PostConstruct
    public void init(){
        System.out.println("Metodo executado depois do construtor");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Metodo executado ao destruir o objeto");
    }
}
