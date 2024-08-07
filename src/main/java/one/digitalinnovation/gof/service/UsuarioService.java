package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Usuario;



public interface UsuarioService {
    Iterable<Usuario> buscarTodos();

    Usuario buscarPorId(Integer id);

    void inserir(Usuario usuario);

    void atualizar(Integer id, String nome, String cep);

    void deletar(Integer id);

}
