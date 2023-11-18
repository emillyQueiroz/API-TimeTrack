package com.timetrack.TimeTrack.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer Id;
    @Column
    private String Nome;
    @Column
    private String Sobrenome;
    @Column
    private String email;
    @Column
    private String senha;

    @JsonIgnore
    @OneToMany(mappedBy ="usuario", fetch = FetchType.LAZY)
    private List<Agenda> agenda;
}
