package br.ifmg.projeto1_2026.service;

import br.ifmg.projeto1_2026.dto.ProdutoDTO;
import br.ifmg.projeto1_2026.entity.Produto;
import br.ifmg.projeto1_2026.repository.ProdutoRepository;
import br.ifmg.projeto1_2026.service.exception.ErroNoBancoDeDados;
import br.ifmg.projeto1_2026.service.exception.RegistroNaoEncontrado;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Page<ProdutoDTO> findAll(Pageable pageable){
        Page<Produto> produtos = produtoRepository.findAll(pageable);
        return produtos.map(ProdutoDTO::new);
    }

    @Transactional(readOnly = true)
    public ProdutoDTO findById(Long id){
        Optional<Produto> opt = produtoRepository.findById(id);
        Produto produto = opt.orElseThrow(() -> new RegistroNaoEncontrado("Produto não encontrado!"));
        return new ProdutoDTO(produto);
    }

    @Transactional
    public ProdutoDTO insert(ProdutoDTO produtoDTO){
        Produto entity = new Produto();
        entity.setNome(produtoDTO.getNome());
        entity.setDescricao(produtoDTO.getDescricao());
        entity.setPreco(produtoDTO.getPreco());
        entity.setImgUrl(produtoDTO.getImgUrl());

        Produto novo = produtoRepository.save(entity);
        return new ProdutoDTO(novo);
    }

    @Transactional
    public void delete (Long id){
        if(!produtoRepository.existsById(id)){
            throw new RegistroNaoEncontrado("Produto não encontrado!");
        }

        try{
            produtoRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new ErroNoBancoDeDados(e.getMessage());
        }
    }

    public ProdutoDTO update(Long id, ProdutoDTO dto){
        if(!produtoRepository.existsById(id)){
            throw new RegistroNaoEncontrado("Produto não encontrado!");
        }

        Produto entity = produtoRepository.getReferenceById(id);

        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setPreco(dto.getPreco());
        entity.setImgUrl(dto.getImgUrl());

        entity = produtoRepository.save(entity);

        return new ProdutoDTO(entity);
    }

}
