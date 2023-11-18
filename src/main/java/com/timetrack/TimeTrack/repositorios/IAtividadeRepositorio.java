package com.timetrack.TimeTrack.repositorios;

import com.timetrack.TimeTrack.models.Agenda;
import com.timetrack.TimeTrack.models.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAtividadeRepositorio extends JpaRepository<Atividade,Integer> {
    List<Atividade> findByAgenda(Agenda agenda);
}
