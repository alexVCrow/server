package com.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="gamesSteps")
@Getter
@Setter
@ToString
public class GamesSteps implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="ref_game")
    private String refGame;
    @Column(name="number_gamer")
    private String numberGamer;
    @Column(name="mat_col")
    private String matCol;
    @Column(name="qty")
    private String qty;
}
