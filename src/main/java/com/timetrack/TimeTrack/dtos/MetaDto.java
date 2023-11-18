package com.timetrack.TimeTrack.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MetaDto {
    private String categoria;
    private Date dataInicio;
    private Date dataConclusao;
    private float valorTotal;
    private float valorGuardo;
    private Integer agenda;
    public MetaDto(String categoria, Date dataInicio, Date dataConclusao, float valorTotal, float valorGuardo, Integer agendaId) {
        this.categoria = categoria;
        this.dataInicio = dataInicio;
        this.dataConclusao = dataConclusao;
        this.valorTotal = valorTotal;
        this.valorGuardo = valorGuardo;
        this.agenda = agendaId;
    }
}
