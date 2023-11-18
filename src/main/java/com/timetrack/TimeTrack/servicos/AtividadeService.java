package com.timetrack.TimeTrack.servicos;

import com.timetrack.TimeTrack.models.Agenda;
import com.timetrack.TimeTrack.models.Atividade;
import com.timetrack.TimeTrack.repositorios.IAtividadeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {
    @Autowired
    private IAtividadeRepositorio atividadeRepositorio;

    public Atividade criarAtividade(Atividade atividade) {
        return atividadeRepositorio.save(atividade);
    }

    public List<Atividade> listarAtividades(Agenda agenda) {return atividadeRepositorio.findByAgenda(agenda);
    }

    public Optional<Atividade> buscarAtividadePorId(Integer id) {
        return atividadeRepositorio.findById(id);
    }

    public Atividade atualizarAtividade(Atividade novaAtividade, Integer id) {
        Optional<Atividade> atividadeExistente = atividadeRepositorio.findById(id);

        if (atividadeExistente.isPresent()) {
            Atividade atividade = atividadeExistente.get();
            atividade.setCategoria(novaAtividade.getCategoria());
            atividade.setDescricao(novaAtividade.getDescricao());
            atividade.setDataInicio(novaAtividade.getDataInicio());
            atividade.setDataConclusao(novaAtividade.getDataConclusao());
            // Atualizar os campos necessários da atividade com os valores da novaAtividade
            // Exemplo: atividade.setCampo(novaAtividade.getCampo());
            return atividadeRepositorio.save(atividade);
        } else {
            // Lidar com o caso em que a atividade com o ID fornecido não foi encontrada
            return null;
        }
    }

    public void deletarAtividade(Integer id) {
        atividadeRepositorio.deleteById(id);
    }
}
