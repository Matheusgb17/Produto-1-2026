package br.ifmg.projeto1_2026.resource;

import br.ifmg.projeto1_2026.entity.Categoria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categoria")

public class CategoriaResource {
    @GetMapping
    public ResponseEntity<List<Categoria>> categoria(){

        Categoria categoria1 = new Categoria(1L, "Notebook");
        Categoria categoria2 = new Categoria(2L, "Tablet");
        Categoria categoria3 = new Categoria(3L, "Livro");
        List<Categoria> categorias = new ArrayList<Categoria>();
        categorias.add(categoria1);
        categorias.add(categoria2);
        categorias.add(categoria3);

        return ResponseEntity.ok().body(categorias);
    };
}
