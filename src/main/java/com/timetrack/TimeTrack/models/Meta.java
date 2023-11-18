package com.timetrack.TimeTrack.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Meta")
public class Meta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer Id;

    @Column
    private String Categoria;
    @Column
    private Date DataInicio;
    @Column
    private Date DataConclusao;
    @Column
    private float ValorTotal;
    @Column
    private float ValorGuardo;

    @ManyToOne
    @JoinColumn(name = "Agenda_id")
    private Agenda agenda;

}
