package com.timetrack.TimeTrack.servicos;
import com.timetrack.TimeTrack.dtos.UsuarioDto;
import com.timetrack.TimeTrack.models.Usuario;
import com.timetrack.TimeTrack.repositorios.IMetaRepositorio;
import com.timetrack.TimeTrack.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepositorio iUsuarioRepositorio;
    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;

    public Usuario criarUsuario(Usuario usuario) {
        // Lógica para criar um usuário
        return usuarioRepositorio.save(usuario);
    }

    public Optional<Usuario> buscarUsuarioPorEmailESenha(String email, String senha) {
        return usuarioRepositorio.findByEmailAndSenha(email, senha);
    }

    public Usuario AtualizaUsuario(UsuarioDto usuarioDto) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(usuarioDto.getId());
        if(usuario.isPresent()) {
            usuario.get().setEmail(usuarioDto.getEmail());
            usuario.get().setSenha(usuarioDto.getSenha());
            usuarioRepositorio.save(usuario.get());
        }
        return usuario.get();
    }
}
