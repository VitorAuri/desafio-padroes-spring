package one.digitalinnovation.gof.service.impl;

import one.digitalinnovation.gof.model.Usuario;
import one.digitalinnovation.gof.model.UsuarioRepository;
import one.digitalinnovation.gof.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Iterable<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        Optional<Usuario> usuarioPorId = usuarioRepository.findById(id);
        return usuarioPorId.get();
    }

    @Override
    public void inserir(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void atualizar(Integer id, Usuario usuario) {
        Optional<Usuario> usuarioParaAtualizar = usuarioRepository.findById(id);
    }

    @Override
    public void deletar(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
