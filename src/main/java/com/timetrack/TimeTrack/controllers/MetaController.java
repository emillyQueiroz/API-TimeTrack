package com.timetrack.TimeTrack.controllers;

import com.timetrack.TimeTrack.dtos.MetaDto;
import com.timetrack.TimeTrack.models.Agenda;
import com.timetrack.TimeTrack.models.Meta;
import com.timetrack.TimeTrack.servicos.AgendaServices;
import com.timetrack.TimeTrack.servicos.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.ClassUtils.isPresent;

@RestController
@RequestMapping("/metas")
public class MetaController {

    @Autowired
    private MetaService metaServices;

    @Autowired
    private AgendaServices agendaServices;

    @PostMapping("/criar")
    public ResponseEntity<Meta> criarMeta(@RequestBody MetaDto metaDto) {
        Meta novaMeta = convertMetaDtoToEntity(metaDto);
        Meta metaCriada = metaServices.criarMeta(novaMeta);
        return new ResponseEntity<>(metaCriada, HttpStatus.CREATED);
    }

    // coloquei o (@PathVariable Integer id semelhante a atividade
    @GetMapping("/listar/{id}")
    public ResponseEntity<List<Meta>> listarMetas(@PathVariable Integer id) {
        Optional<Agenda> agenda = agendaServices.buscarAgendaPorId(id);
        if(!agenda.isPresent())
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        List<Meta> metas = metaServices.listarMetas(agenda.get());
        return new ResponseEntity<>(metas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Meta>> buscarMetaPorId(@PathVariable Integer id) {
        Optional<Meta> meta = metaServices.buscarMetaPorId(id);
        return meta.isPresent() ?
                new ResponseEntity<>(meta, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meta> atualizarMeta(@PathVariable Integer id, @RequestBody MetaDto metaDto) {
        Meta metaAtualizada = convertMetaDtoToEntity(metaDto);
        Meta meta = metaServices.atualizarMeta(metaAtualizada, id);

        return meta != null ?
                new ResponseEntity<>(meta, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMeta(@PathVariable Integer id) {
        metaServices.deletarMeta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Método para converter MetaDto em Meta
    private Meta convertMetaDtoToEntity(MetaDto metaDto) {
        Meta meta = new Meta();
        meta.setCategoria(metaDto.getCategoria());
        meta.setDataInicio(metaDto.getDataInicio());
        meta.setDataConclusao(metaDto.getDataConclusao());
        meta.setValorTotal(metaDto.getValorTotal());
        meta.setValorGuardo(metaDto.getValorGuardo());
        if(metaDto.getAgenda() != null) {
            Optional<Agenda> agenda = agendaServices.buscarAgendaPorId(metaDto.getAgenda());
             if(agenda.isPresent()) {
            meta.setAgenda(agenda.get());
           }
        }
        return meta;
    }
}
