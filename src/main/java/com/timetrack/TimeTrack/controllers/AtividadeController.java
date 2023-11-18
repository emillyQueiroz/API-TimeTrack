package com.timetrack.TimeTrack.controllers;

import com.timetrack.TimeTrack.dtos.AtividadeDto;
import com.timetrack.TimeTrack.models.Agenda;
import com.timetrack.TimeTrack.models.Atividade;
import com.timetrack.TimeTrack.servicos.AgendaServices;
import com.timetrack.TimeTrack.servicos.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;
    @Autowired
    private AgendaServices agendaServices;

    @PostMapping("/criar")
    public ResponseEntity<Atividade> criarAtividade(@RequestBody AtividadeDto atividadeDto) {
        Atividade novaAtividade = convertAtividadeDtoToEntity(atividadeDto);
        Atividade atividadeCriada = atividadeService.criarAtividade(novaAtividade);
        return new ResponseEntity<>(atividadeCriada, HttpStatus.CREATED);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<List<Atividade>> listarAtividades(@PathVariable Integer id) {
        Optional<Agenda> agenda = agendaServices.buscarAgendaPorId(id);
        if(!agenda.isPresent())
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        List<Atividade> atividades = atividadeService.listarAtividades(agenda.get());
        return new ResponseEntity<>(atividades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Atividade>> buscarAtividadePorId(@PathVariable Integer id) {
        Optional<Atividade> atividade = atividadeService.buscarAtividadePorId(id);
        return atividade.isPresent() ?
                new ResponseEntity<>(atividade, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atividade> atualizarAtividade(@PathVariable Integer id, @RequestBody AtividadeDto atividadeDto) {
        Atividade atividadeAtualizada = convertAtividadeDtoToEntity(atividadeDto);
        Atividade atividade = atividadeService.atualizarAtividade(atividadeAtualizada, id);

        return atividade != null ?
                new ResponseEntity<>(atividade, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtividade(@PathVariable Integer id) {
        atividadeService.deletarAtividade(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Método para converter AtividadeDto em Atividade
        private Atividade convertAtividadeDtoToEntity(AtividadeDto atividadeDto) {
            Atividade atividade = new Atividade();
            atividade.setCategoria(atividadeDto.getCategoria());
            atividade.setDataInicio(atividadeDto.getDataInicio());
            atividade.setDataConclusao(atividadeDto.getDataConclusao());
            atividade.setDescricao(atividadeDto.getDescricao());
            if(atividadeDto.getAgenda() != null) {
                Optional<Agenda> agenda = agendaServices.buscarAgendaPorId(atividadeDto.getAgenda());
                if (agenda.isPresent()) {
                    atividade.setAgenda(agenda.get());
                }
            }
            return atividade;
    }
}
