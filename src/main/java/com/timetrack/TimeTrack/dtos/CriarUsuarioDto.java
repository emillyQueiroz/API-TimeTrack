package com.timetrack.TimeTrack.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriarUsuarioDto {
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String confirmacaoSenha;

    public CriarUsuarioDto(String nome, String sobrenome, String email, String senha, String confirmacaoSenha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.confirmacaoSenha = confirmacaoSenha;
    }
}
