package com.timetrack.TimeTrack.servicos;

import com.timetrack.TimeTrack.models.Agenda;
import com.timetrack.TimeTrack.models.Atividade;
import com.timetrack.TimeTrack.models.Meta;
import com.timetrack.TimeTrack.repositorios.IMetaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetaService {
    @Autowired
    private IMetaRepositorio metaRepositorio;

    public Meta criarMeta(Meta meta) {
        return metaRepositorio.save(meta);
    }

    public List<Meta> listarMetas(Agenda agenda) {return metaRepositorio.findByAgenda(agenda);
    }

    public Optional<Meta> buscarMetaPorId(Integer id) {return metaRepositorio.findById(id);
    }
    public Meta atualizarMeta(Meta novaMeta, Integer id) {
        Optional<Meta> metaExistente = metaRepositorio.findById(id);

        if (metaExistente.isPresent()) {
            Meta meta = metaExistente.get();
            meta.setCategoria(novaMeta.getCategoria());
            meta.setDataInicio(novaMeta.getDataInicio());
            meta.setDataConclusao(novaMeta.getDataConclusao());
            meta.setValorGuardo(novaMeta.getValorGuardo());
            meta.setValorTotal(novaMeta.getValorTotal());
            return metaRepositorio.save(meta);
        } else {
            // Lidar com o caso em que a meta com o ID fornecido não foi encontrada
            return null;
        }
    }

    public void deletarMeta(Integer id) {
        metaRepositorio.deleteById(id);
    }
}
