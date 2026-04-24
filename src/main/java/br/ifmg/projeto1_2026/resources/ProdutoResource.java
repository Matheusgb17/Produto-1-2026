package br.ifmg.projeto1_2026.resources;

import br.ifmg.projeto1_2026.dto.ProdutoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Tag(name="Produtos", description = "Essa API é responsável por gerenciar produtos na plataforma.")
public class ProdutoResource {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoResource.class);

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(produces = "application/json")
    @Operation(
            summary = "Endpoint para listar produtos",
            description = "Retorna uma página com a listagem de todos os produtos cadastrados no sistema.",
            responses = {
                    @ApiResponse(description = "Listagem retornada com sucesso!", responseCode = "200")
            }
    )
    public ResponseEntity<Page<ProdutoDTO>> produtos(Pageable pageable){
        Page<ProdutoDTO> produtos = produtoService.findAll(pageable);

        logger.info("Consltando a lista de produtos");
        logger.warn("Consltando a lista de produtos");
        logger.error("Consltando a {} de produtos", "lista");

        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(
            summary = "Endpoint para buscar um produto por ID",
            description = "Busca os detalhes de um produto específico através do seu identificador único.",
            responses = {
                    @ApiResponse(description = "Produto encontrado e retornado com sucesso!", responseCode = "200"),
                    @ApiResponse(description = "Produto não encontrado no sistema", responseCode = "404")
            }
    )
    public ResponseEntity<ProdutoDTO> produto(@PathVariable Long id){
        ProdutoDTO dto = produtoService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @Operation(
            summary = "Endpoint para inserir um produto",
            description = "Recebe os dados no corpo da requisição e cadastra um novo produto na plataforma.",
            responses = {
                    @ApiResponse(description = "Produto criado com sucesso!", responseCode = "201"),
                    @ApiResponse(description = "Erro de validação dos dados", responseCode = "400"),
                    @ApiResponse(description = "Erro interno do servidor", responseCode = "500")
            }
    )
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
    @Operation(
            summary = "Endpoint para deletar um produto",
            description = "Remove um produto específico do banco de dados utilizando o seu ID.",
            responses = {
                    @ApiResponse(description = "Produto deletado com sucesso!", responseCode = "204"),
                    @ApiResponse(description = "Produto não encontrado para deleção", responseCode = "404")
            }
    )
    public ResponseEntity<Void> delete(@PathVariable Long id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="/{id}", produces = "application/json", consumes = "application/json")
    @Operation(
            summary = "Endpoint para atualizar um produto",
            description = "Atualiza as informações de um produto já existente com base no ID fornecido.",
            responses = {
                    @ApiResponse(description = "Produto atualizado com sucesso!", responseCode = "200"),
                    @ApiResponse(description = "Requisição mal formatada ou dados inválidos", responseCode = "400"),
                    @ApiResponse(description = "Produto não encontrado no sistema", responseCode = "404")
            }
    )
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO dto){
        ProdutoDTO ret = produtoService.update(id, dto);
        return ResponseEntity.ok().body(ret);
    }

}