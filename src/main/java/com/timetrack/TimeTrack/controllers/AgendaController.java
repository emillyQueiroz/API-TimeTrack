package com.timetrack.TimeTrack.controllers;
import com.timetrack.TimeTrack.models.Usuario;

import com.timetrack.TimeTrack.dtos.AgendaDto;
import com.timetrack.TimeTrack.models.Agenda;
import com.timetrack.TimeTrack.repositorios.IUsuarioRepositorio;
import com.timetrack.TimeTrack.servicos.AgendaServices;
import com.timetrack.TimeTrack.servicos.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agendas")
public class AgendaController {

    @Autowired
    private AgendaServices agendaServices;
    @Autowired
    private IUsuarioRepositorio usuarioRepositorio;

    @PostMapping("/criar")
    public ResponseEntity<Agenda> criarAgenda(@RequestBody AgendaDto agendaDto) {
        try {
            Agenda novaAgenda = convertAgendaDtoToEntity(agendaDto);
            Agenda agendaCriada = agendaServices.criarAgenda(novaAgenda);
            agendaCriada.setUsuario(null);
            return new ResponseEntity<>(agendaCriada, HttpStatus.CREATED);
        }catch (Exception ex){
            throw ex;
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<List<Agenda>> listarAgendas(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        if(usuario.isPresent()) {
            List<Agenda> agendas = agendaServices.listarAgendas(usuario.get());
            return new ResponseEntity<>(agendas, HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Agenda>> buscarAgendaPorId(@PathVariable Integer id) {
        Optional<Agenda> agenda = agendaServices.buscarAgendaPorId(id);
        return agenda.isPresent() ?
                new ResponseEntity<>(agenda, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgenda(@PathVariable Integer id) {
        agendaServices.deletarAgenda(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Método para converter AgendaDto em Agenda
    private Agenda convertAgendaDtoToEntity(AgendaDto agendaDto) {
        Agenda agenda = new Agenda();
        agenda.setNome(agendaDto.getNome());
        // Adicione outros campos, se houver
        Optional<Usuario> usuario = usuarioRepositorio.findById(agendaDto.getUsuario());
        if(usuario.isPresent())
            agenda.setUsuario(usuario.get());
        return agenda;
    }
}
