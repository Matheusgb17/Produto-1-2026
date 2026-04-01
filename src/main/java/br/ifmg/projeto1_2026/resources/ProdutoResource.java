package br.ifmg.projeto1_2026.resources;

import br.ifmg.projeto1_2026.dto.ProdutoDTO;
import org.springframework.data.domain.Page;
import br.ifmg.projeto1_2026.service.ProdutoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.data.domain.Pageable;
import java.net.URI;

@RestController
@RequestMapping("/produto")

public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> produtos(Pageable pageable){
        Page<ProdutoDTO> produtos = produtoService.findAll(pageable);
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> produto(@PathVariable Long id){
        ProdutoDTO dto = produtoService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> insert(@RequestBody ProdutoDTO dto){
        ProdutoDTO retorno = produtoService.insert(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(retorno.getId())
                .toUri();

        return ResponseEntity.created(location).body(retorno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO dto){
        ProdutoDTO ret = produtoService.update(id, dto);
        return ResponseEntity.ok().body(ret);
    }


}
