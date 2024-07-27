package one.digitalinnovation.gof.controller;

import one.digitalinnovation.gof.model.Usuario;
import one.digitalinnovation.gof.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Controller de Usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Listagem de Usuarios", description = "Retorna uma lista dos usuarios cadastrados.")
    public List<Usuario> listaDeUsuarios() {
        return (List<Usuario>) usuarioService.buscarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Usuario por ID", description = "Retorna usuario com ID correspondente.")
    public Usuario usuarioPorId(@PathVariable Integer id) {
        return usuarioService.buscarPorId(id);
    }

    @PostMapping
    @Operation(summary = "Criar Usuario", description = "Cadastra um novo usuario no sistema através de um POST.")
    public void criarUsuario(@RequestBody Usuario usuario) {
        usuarioService.inserir(usuario);
    }

    @DeleteMapping
    @Operation(summary = "Deletar Usuario", description = "Deleta um usuario com ID correspondente.")
    public void deletarUsuario(@RequestBody Integer id) {
        usuarioService.deletar(id);
    }

    @PutMapping
    @Operation(summary = "Editar Usuario", description = "Edita um usuario com ID correspondente.")
    public void editarUsuario(@RequestBody Integer id, String nome, String cep) {
        usuarioService.atualizar(id, nome, cep);
    }

}
