package com.timetrack.TimeTrack.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class AtividadeDto
{
    private String categoria;
    private Date dataInicio;
    private Date dataConclusao;
    private Integer atividade;
    private String Descricao;
    private Integer agenda;

    public AtividadeDto(String categoria, Date dataInicio, Date dataConclusao, Integer atividade, String descricao, Integer agenda) {
        this.categoria = categoria;
        this.dataInicio = dataInicio;
        this.dataConclusao = dataConclusao;
        this.atividade = atividade;
        Descricao = descricao;
        this.agenda = agenda;
    }
}
