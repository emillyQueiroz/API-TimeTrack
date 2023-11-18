package com.timetrack.TimeTrack.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "Agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer Id;

    @Column
    private String Nome;

    @JsonIgnore
    @OneToMany(mappedBy ="agenda", fetch = FetchType.LAZY)
    private List<Meta> Metas;

    @JsonIgnore
    @OneToMany(mappedBy ="agenda", fetch = FetchType.LAZY)
    private List<Atividade> Atividades;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
