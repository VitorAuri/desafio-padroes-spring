package one.digitalinnovation.gof.controller;

import one.digitalinnovation.gof.model.Usuario;
import one.digitalinnovation.gof.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listaDeUsuarios() {
        return (List<Usuario>) usuarioService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Usuario usuarioPorId(@PathVariable Integer id) {
        return usuarioService.buscarPorId(id);
    }

    @PostMapping
    public void criarUsuario(@RequestBody Usuario usuario) {
        usuarioService.inserir(usuario);
    }

}
