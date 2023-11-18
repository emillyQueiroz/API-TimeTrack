package com.timetrack.TimeTrack.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {
    private Integer id;
    private String email;
    private String senha;
}
