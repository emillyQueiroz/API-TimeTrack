package com.timetrack.TimeTrack.repositorios;

import com.timetrack.TimeTrack.models.Agenda;
import com.timetrack.TimeTrack.models.Atividade;
import com.timetrack.TimeTrack.models.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMetaRepositorio extends JpaRepository<Meta,Integer> {
    List<Meta> findByAgenda(Agenda agenda);
}
