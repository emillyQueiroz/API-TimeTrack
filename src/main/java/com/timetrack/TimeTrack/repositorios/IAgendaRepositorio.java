package com.timetrack.TimeTrack.repositorios;

import com.timetrack.TimeTrack.models.Agenda;
import com.timetrack.TimeTrack.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAgendaRepositorio extends JpaRepository<Agenda,Integer> {
    List<Agenda> findByUsuario(Usuario usuario);

}
