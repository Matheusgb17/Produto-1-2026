package br.ifmg.projeto1_2026.resources;

import br.ifmg.projeto1_2026.entity.Usuario;
import br.ifmg.projeto1_2026.service.AtivacaoClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/venda")
public class VendasResource {
    private AtivacaoClienteService ativacaoCliente;

    public VendasResource(AtivacaoClienteService ativacaoCliente){
        this.ativacaoCliente = ativacaoCliente;
        System.out.println("Camada de resource criada");
    }

    @PostMapping
    public ResponseEntity<String> insert(){
        Usuario usuario = new Usuario();

        usuario.setNome("Fernando");
        usuario.setTelefone("11873847362");
        usuario.setEmail("Fernando@gmai.com");
        ativacaoCliente.ativar(usuario, "ativando...");
        return ResponseEntity.ok().body("Venda criada");
    }
}