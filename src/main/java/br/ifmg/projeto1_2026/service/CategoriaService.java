package br.ifmg.projeto1_2026.service;

import br.ifmg.projeto1_2026.dto.CategoriaDTO;
import br.ifmg.projeto1_2026.entity.Categoria;
import br.ifmg.projeto1_2026.repository.CategoriaRepository;
import br.ifmg.projeto1_2026.service.exception.ErroNoBancoDeDados;
import br.ifmg.projeto1_2026.service.exception.RegistroNaoEncontrado;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Page<CategoriaDTO> findAll(Pageable pageable){
        Page<Categoria> categorias = categoriaRepository.findAll(pageable);

        return categorias.map(CategoriaDTO::new);
    }


    @Transactional(readOnly = true)
    public CategoriaDTO findById(Long id) {
        //buscamos no bd a categoria. O resultado é um objeto do tipo Optional
        Optional<Categoria> opt = categoriaRepository.findById(id);

        //buscamos a categoria dentro do objeto Optional
        Categoria categoria = opt.orElseThrow(()->new RegistroNaoEncontrado("Categoria não encontrada"));

        //convertemos a entidade de DTO
        return new CategoriaDTO(categoria);
    }

    public CategoriaDTO insert(CategoriaDTO dto){

        Categoria entity = new Categoria();
        entity.setNome(dto.getNome());
        Categoria nova = categoriaRepository.save(entity);
        return new CategoriaDTO(nova);
    }

    @Transactional
    public void delete(Long id){
        if(!categoriaRepository.existsById(id)){
            throw new RegistroNaoEncontrado("Categoria não encontrada ao ser excluída.");
        }
        try{
            categoriaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new ErroNoBancoDeDados(e.getMessage());
        }
    }

    public CategoriaDTO update(Long id, CategoriaDTO dto){
        if(!categoriaRepository.existsById(id)){
            throw new RegistroNaoEncontrado("Categoria não encontrada.");
        }

        Categoria entity = categoriaRepository.getReferenceById(id);

        entity.setNome(dto.getNome());
        entity = categoriaRepository.save(entity);
        return new CategoriaDTO(entity);

    }
}
