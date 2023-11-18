package com.timetrack.TimeTrack.servicos;

import com.timetrack.TimeTrack.models.Agenda;
import com.timetrack.TimeTrack.models.Usuario;

import com.timetrack.TimeTrack.repositorios.IAgendaRepositorio;
import com.timetrack.TimeTrack.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaServices {

    @Autowired
    private IAgendaRepositorio agendaRepositorio;

    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;

    public Agenda criarAgenda(Agenda agenda) {
        return agendaRepositorio.save(agenda);
    }

    public List<Agenda> listarAgendas(Usuario usuario) {
        return agendaRepositorio.findByUsuario(usuario);
    }

    public Optional<Agenda> buscarAgendaPorId(Integer id) {
        return agendaRepositorio.findById(id);
    }

    public void deletarAgenda(Integer id) {
        agendaRepositorio.deleteById(id);
    }
}
