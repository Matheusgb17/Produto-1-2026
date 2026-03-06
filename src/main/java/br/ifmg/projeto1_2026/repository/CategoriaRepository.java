package br.ifmg.projeto1_2026.repository;

import br.ifmg.projeto1_2026.entity.Categoria;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
