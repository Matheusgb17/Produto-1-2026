package br.ifmg.projeto1_2026.repository;

import br.ifmg.projeto1_2026.entity.Categoria;
import br.ifmg.projeto1_2026.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
