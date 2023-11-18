package com.timetrack.TimeTrack.repositorios;

import com.timetrack.TimeTrack.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepositorio extends JpaRepository<Usuario,Integer> {
    Optional<Usuario> findByEmailAndSenha(String email, String senha);
}
