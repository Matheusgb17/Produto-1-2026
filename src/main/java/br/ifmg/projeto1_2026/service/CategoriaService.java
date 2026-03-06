package br.ifmg.projeto1_2026.service;

import br.ifmg.projeto1_2026.entity.Categoria;
import br.ifmg.projeto1_2026.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }
}
