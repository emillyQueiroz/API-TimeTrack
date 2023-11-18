package com.timetrack.TimeTrack.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendaDto {
    private String nome;
    private Integer usuario;

    public AgendaDto(String nome, Integer usuario) {
        this.nome = nome;
        this.usuario = usuario;
    }
}
