package com.example.dto;

import com.example.entity.GamesSteps;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class GameRequest implements Serializable {

    private Map<String,Integer> useNumber;
    private GamesSteps gamesSteps;

}
