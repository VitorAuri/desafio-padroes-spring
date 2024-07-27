package one.digitalinnovation.gof.service.impl;

import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.model.Usuario;
import one.digitalinnovation.gof.model.UsuarioRepository;
import one.digitalinnovation.gof.service.UsuarioService;
import one.digitalinnovation.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

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
        salvarUsuarioComCep(usuario);
    }


    @Override
    public void atualizar(Integer id, String nome, String cep) {
        Usuario usuarioParaAtualizar = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + id + " não encontrado"));

        usuarioParaAtualizar.setNome(nome);

        // Consultar o novo endereço pelo CEP
        Endereco endereco = viaCepService.consultarCep(cep);
        Endereco enderecoExistente = enderecoRepository.findById(endereco.getCep()).orElse(null);

        if (enderecoExistente == null) {
            // Se o endereço não existir, salvá-lo
            endereco = enderecoRepository.save(endereco);
        } else {
            // Se o endereço já existir, usar o existente
            endereco = enderecoExistente;
        }

        // Atualizar o endereço do usuário
        usuarioParaAtualizar.setEndereco(endereco);

        usuarioRepository.save(usuarioParaAtualizar);
    }


    @Override
    public void deletar(Integer id) {
        usuarioRepository.deleteById(id);
    }

    private void salvarUsuarioComCep(Usuario usuario) {
        // Consultar o endereço pelo CEP
        Endereco endereco = viaCepService.consultarCep(usuario.getEndereco().getCep());

        // Verificar se o endereço já existe no banco de dados
        Endereco enderecoExistente = enderecoRepository.findById(endereco.getCep()).orElse(null);

        if (enderecoExistente == null) {
            // Se o endereço não existir, salvá-lo
            endereco = enderecoRepository.save(endereco);
        } else {
            // Se o endereço já existir, usar o existente
            endereco = enderecoExistente;
        }

        // Associar o endereço ao usuário
        usuario.setEndereco(endereco);

        // Salvar o usuário com o endereço associado
        usuarioRepository.save(usuario);
    }

}
