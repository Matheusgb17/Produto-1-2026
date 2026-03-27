package br.ifmg.projeto1_2026.resources;

import br.ifmg.projeto1_2026.dto.CategoriaDTO;
import br.ifmg.projeto1_2026.service.CategoriaService;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.print.Pageable;
import java.net.URI;

@RestController
@RequestMapping("/categoria")

public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Page<CategoriaDTO>> categorias(Pageable pageable) {
        Page<CategoriaDTO> categorias = categoriaService.findAll(pageable);
        return ResponseEntity.ok().body(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> categoria(@PathVariable Long id){
        CategoriaDTO dto = categoriaService.findById(id);

        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> insert(@RequestBody CategoriaDTO dto){
        CategoriaDTO retorno = categoriaService.insert(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(retorno.getId())
                .toUri();

        return ResponseEntity.created(location).body(retorno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @RequestBody CategoriaDTO dto){
        CategoriaDTO ret = categoriaService.update(id, dto);
        return ResponseEntity.ok().body(ret);
    }

}