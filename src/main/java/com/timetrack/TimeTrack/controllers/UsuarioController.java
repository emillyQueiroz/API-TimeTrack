package com.timetrack.TimeTrack.controllers;
import com.timetrack.TimeTrack.dtos.CriarUsuarioDto;
import com.timetrack.TimeTrack.dtos.MetaDto;
import com.timetrack.TimeTrack.dtos.UsuarioDto;
import com.timetrack.TimeTrack.models.Meta;
import com.timetrack.TimeTrack.models.Usuario;
import com.timetrack.TimeTrack.servicos.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioServices;

    @PostMapping("/criar")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody CriarUsuarioDto usuarioDto) {

        // Validação de senha e confirmação de senha
        if (!usuarioDto.getSenha().equals(usuarioDto.getConfirmacaoSenha())) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        // Criar um objeto Usuario a partir do DTO e passar para o serviço
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(usuarioDto.getNome());
        novoUsuario.setSobrenome(usuarioDto.getSobrenome());
        novoUsuario.setEmail(usuarioDto.getEmail());
        novoUsuario.setSenha(usuarioDto.getSenha());

        // Usar o serviço para criar o usuário
        Usuario usuarioCriado = usuarioServices.criarUsuario(novoUsuario);

        return new ResponseEntity<>(usuarioCriado, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<Integer> fazerLogin(@RequestParam String email, @RequestParam String senha) {
        // Verificar se o usuário com o email e senha fornecidos existe
        Optional<Usuario> usuarioOptional = usuarioServices.buscarUsuarioPorEmailESenha(email, senha);

        if (usuarioOptional.isPresent()) {
            Integer idUsuario = usuarioOptional.get().getId();
            return new ResponseEntity<>(idUsuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping()
    public ResponseEntity<Usuario> atualizarUsuario(@RequestBody UsuarioDto usuarioDto) {
        Usuario usuario = usuarioServices.AtualizaUsuario(usuarioDto);

        return usuario != null ?
                new ResponseEntity<>(usuario, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

