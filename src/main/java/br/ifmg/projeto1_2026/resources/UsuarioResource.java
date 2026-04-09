package br.ifmg.projeto1_2026.resources;

import br.ifmg.projeto1_2026.dto.UsuarioDTO;
import br.ifmg.projeto1_2026.entity.Usuario;
import br.ifmg.projeto1_2026.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")

public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<UsuarioDTOa>> usuario(Pageable pageable){
        Page<UsuarioDTO> usuarios =  usuarioService.findAll(pageable);
        return  ResponseEntity.ok().body(usuarios);
    };

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> usuario(@PathVariable Long id){
        UsuarioDTO dto = usuarioService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> insert(@RequestBody UsuarioDTO dto){
        UsuarioDTO retorno = usuarioService.insert(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(retorno.getId()).toUri();
        return ResponseEntity.created(location).body(retorno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        usuarioService.delete(id);
        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO dto){
        UsuarioDTO retorno = usuarioService.update(id, dto);
        return ResponseEntity.ok().body(retorno);
    }
}
