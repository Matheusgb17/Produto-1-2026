package br.ifmg.projeto1_2026.service;

import br.ifmg.projeto1_2026.dto.ProdutoDTO;
import br.ifmg.projeto1_2026.entity.Produto;
import br.ifmg.projeto1_2026.repository.ProdutoRepository;
import br.ifmg.projeto1_2026.resources.ProdutoResource;
import br.ifmg.projeto1_2026.service.exception.ErroNoBancoDeDados;
import br.ifmg.projeto1_2026.service.exception.RegistroNaoEncontrado;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Page<ProdutoDTO> findAll(Pageable pageable){
        Page<Produto> produtos = produtoRepository.findAll(pageable);

        return produtos.map(
                p -> new ProdutoDTO(p)
                        .add(linkTo(methodOn(ProdutoResource.class).produtos(null)).withSelfRel())
                        .add(linkTo(methodOn(ProdutoResource.class).produto(p.getId())).withRel("Obter Produto pelo ID"))
        );
    }

    @Transactional(readOnly = true)
    public ProdutoDTO findById(Long id){
        Optional<Produto> opt = produtoRepository.findById(id);
        Produto produto = opt.orElseThrow(() -> new RegistroNaoEncontrado("Produto não encontrado!"));

        ProdutoDTO dto = new ProdutoDTO(produto);

        return dto
                .add(linkTo(methodOn(ProdutoResource.class).produto(produto.getId())).withSelfRel())
                .add(linkTo(methodOn(ProdutoResource.class).produtos(null)).withRel("Todos os produtos"))
                .add(linkTo(methodOn(ProdutoResource.class).update(produto.getId(), dto)).withRel("Atualizar Produto"))
                .add(linkTo(methodOn(ProdutoResource.class).delete(produto.getId())).withRel("Apagar Produto"));
    }

    @Transactional
    public ProdutoDTO insert(ProdutoDTO produtoDTO){
        Produto entity = new Produto();
        entity.setNome(produtoDTO.getNome());
        entity.setDescricao(produtoDTO.getDescricao());
        entity.setPreco(produtoDTO.getPreco());
        entity.setImgUrl(produtoDTO.getImgUrl());

        Produto novo = produtoRepository.save(entity);
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));

        return new ProdutoDTO(novo)
                .add(linkTo(methodOn(ProdutoResource.class).produto(novo.getId())).withSelfRel())
                .add(linkTo(methodOn(ProdutoResource.class).produtos(pageable)).withRel("Todos os produtos"))
                .add(linkTo(methodOn(ProdutoResource.class).update(novo.getId(), produtoDTO)).withRel("Atualizar Produto"))
                .add(linkTo(methodOn(ProdutoResource.class).delete(novo.getId())).withRel("Apagar Produto"));
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

        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));

        return new ProdutoDTO(entity)
                .add(linkTo(methodOn(ProdutoResource.class).produto(entity.getId())).withSelfRel())
                .add(linkTo(methodOn(ProdutoResource.class).produto(entity.getId())).withRel("Busca pelo ID"))
                .add(linkTo(methodOn(ProdutoResource.class).produtos(pageable)).withRel("Todos os produtos"))
                .add(linkTo(methodOn(ProdutoResource.class).delete(id)).withRel("Apagar Produto"));
    }

}
