package br.ifmg.projeto1_2026.service;

import br.ifmg.projeto1_2026.dto.PerfilDTO;
import br.ifmg.projeto1_2026.dto.UsuarioDTO;
import br.ifmg.projeto1_2026.entity.Perfil;
import br.ifmg.projeto1_2026.entity.Usuario;
import br.ifmg.projeto1_2026.repository.PerfilRepository;
import br.ifmg.projeto1_2026.repository.UsuarioRepository;
import org.springframework.data.domain.Pageable;
import br.ifmg.projeto1_2026.service.exception.ErroNoBancoDeDados;
import br.ifmg.projeto1_2026.service.exception.RegistroNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Transactional(readOnly = true)
    public Page<UsuarioDTO> findAll(Pageable pageable){
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        return  usuarios.map(UsuarioDTO::new);
    }

    public UsuarioDTO findById(Long id){
        Optional<Usuario> opt = usuarioRepository.findById(id);
        Usuario usuario = opt.orElseThrow(() ->new RegistroNaoEncontrado("Usuário não encontrado"));
        return new UsuarioDTO(usuario);
    }

    @Transactional
    public UsuarioDTO insert(UsuarioDTO dto){
        Usuario entity = new Usuario();
        entity.setNome(dto.getNome());
        entity.setTelefone(dto.getTelefone());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());

        entity.getPerfil().clear();

        for(PerfilDTO perfDto: dto.getPerfil()){
            Perfil perf = perfilRepository.getReferenceById(perfDto.getId());
            entity.getPerfil().add(perf);
        }

        Usuario novo = usuarioRepository.save(entity);
        return new UsuarioDTO(novo);
    }

    @Transactional
    public void delete(Long id){
        if(!usuarioRepository.existsById(id)){
            throw new RegistroNaoEncontrado("Usuário não encontrado para ser excluído");
        }

        try{
            usuarioRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new ErroNoBancoDeDados(e.getMessage());
        }
    }

    public UsuarioDTO update(Long id, UsuarioDTO dto){
        if(!usuarioRepository.existsById(id)){
            throw new RegistroNaoEncontrado("Usuário não encontrado para ser alterado");
        }

        Usuario entity = usuarioRepository.getReferenceById(id);
        entity = copyDtoToEntity(dto, entity);
        return new UsuarioDTO(entity);
    }

    @NonNull
    private Usuario copyDtoToEntity(UsuarioDTO dto, Usuario entity){
        entity.setNome(dto.getNome());
        entity.setTelefone(dto.getTelefone());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        entity = usuarioRepository.save(entity);

        entity.getPerfil().clear();
        for(PerfilDTO perfDto: dto.getPerfil()){
            Perfil perf = perfilRepository.getReferenceById(perfDto.getId());
            entity.getPerfil().add(perf);
        }
        return entity;

    }

}
